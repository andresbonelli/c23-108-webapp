package tech.nocountry.roadbites.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.nocountry.roadbites.controller.dto.order.OrderMenuResponseDTO;
import tech.nocountry.roadbites.controller.dto.order.OrderResponseDTO;
import tech.nocountry.roadbites.controller.dto.order.PlaceOrderDTO;
import tech.nocountry.roadbites.domain.model.*;
import tech.nocountry.roadbites.domain.repository.OrderMenuRepository;
import tech.nocountry.roadbites.domain.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final UserService userService;
    private final MenuService menuService;

    public OrderResponseDTO placeOrder(PlaceOrderDTO order) {
        Order newOrder = buildOrderFromDto(order);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(newOrder);
        order.orderMenus().forEach(orderMenu -> {
            Menu menu = menuService.getMenuById(orderMenu.menuId());
            OrderMenu newOrderMenu = OrderMenu.builder()
                    .order(savedOrder)
                    .menu(menu)
                    .quantity(orderMenu.quantity())
                    .build();
            orderMenuRepository.save(newOrderMenu);
        }
        );
        return buildResponseDtoFromOrder(savedOrder);
    }

    public List<OrderResponseDTO> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::buildResponseDtoFromOrder).toList();
    }

    public List<OrderResponseDTO> getAllByUser(String userName) {
        List<Order> orders = orderRepository.findByUserName(userName);
        return orders.stream().map(this::buildResponseDtoFromOrder).toList();
    }

    private Order buildOrderFromDto(PlaceOrderDTO order) {
        return Order.builder()
                .userName(order.userName())
                .build();
    }

    private OrderResponseDTO buildResponseDtoFromOrder(Order order) {
        List<OrderMenu> orderMenus = orderMenuRepository.findByOrder(order);
        return new OrderResponseDTO(
                order.getId(),
                order.getUserName(),
                order.getOrderDate(),
                order.getStatus(),
                orderMenus.stream().map(orderMenu -> new OrderMenuResponseDTO(
                        orderMenu.getMenu().getId(),
                        orderMenu.getQuantity()
                )).toList()
        );
    }
}
