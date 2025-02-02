package tech.nocountry.roadbites.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.nocountry.roadbites.controller.dto.CreateOrderMenuDTO;
import tech.nocountry.roadbites.controller.dto.PlaceOrderDTO;
import tech.nocountry.roadbites.domain.model.Menu;
import tech.nocountry.roadbites.domain.model.Order;
import tech.nocountry.roadbites.domain.model.OrderMenu;
import tech.nocountry.roadbites.domain.model.User;
import tech.nocountry.roadbites.domain.repository.OrderMenuRepository;
import tech.nocountry.roadbites.domain.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final UserService userService;
    private final MenuService menuService;

    public Order placeOrder(PlaceOrderDTO order) {
        Order newOrder = buildOrderFromDto(order);
        newOrder.setOrderDate(LocalDateTime.now());
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
        return savedOrder;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    private Order buildOrderFromDto(PlaceOrderDTO order) {
        User user = userService.getUserById(order.userId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return Order.builder()
                .user(user)
                .build();
    }
}
