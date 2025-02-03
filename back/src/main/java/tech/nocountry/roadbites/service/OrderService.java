package tech.nocountry.roadbites.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import tech.nocountry.roadbites.controller.dto.order.OrderMenuResponseDTO;
import tech.nocountry.roadbites.controller.dto.order.OrderResponseDTO;
import tech.nocountry.roadbites.controller.dto.order.PlaceOrderDTO;
import tech.nocountry.roadbites.domain.model.*;
import tech.nocountry.roadbites.domain.repository.OrderMenuRepository;
import tech.nocountry.roadbites.domain.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final MenuService menuService;
    private final EmailService  emailService;

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
        emailService.sendOrderConfirmationEmail(
                order.userEmail(),
                buildOrderEmailBody(order));
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
                .userEmail(order.userEmail())
                .build();
    }

    private OrderResponseDTO buildResponseDtoFromOrder(Order order) {
        List<OrderMenu> orderMenus = orderMenuRepository.findByOrder(order);
        return new OrderResponseDTO(
                order.getId(),
                order.getUserName(),
                order.getUserEmail(),
                order.getOrderDate(),
                order.getStatus(),
                orderMenus.stream().map(orderMenu -> new OrderMenuResponseDTO(
                        orderMenu.getMenu().getId(),
                        orderMenu.getQuantity()
                )).toList()
        );
    }

    private String buildOrderEmailBody(PlaceOrderDTO order) {
        String orderList = order.orderMenus().stream()
                .map(orderMenu ->
                        "- " + orderMenu.menuName()
                         + " (" + orderMenu.quantity() + ")"
                         + " $" + orderMenu.price() * orderMenu.quantity())
                .collect(Collectors.joining("<br>"));

        return """
                <html>
                <body>
                    <h2>Pedido Confirmado</h2>
                    <p>Tu pedido ha sido recibido con éxito:</p>
                    <p>%s</p>
                    <p>¡Gracias por tu compra!</p>
                </body>
                </html>
                """.formatted(orderList);
    }
}
