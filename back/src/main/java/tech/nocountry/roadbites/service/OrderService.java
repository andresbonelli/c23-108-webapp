package tech.nocountry.roadbites.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import tech.nocountry.roadbites.controller.dto.order.CreateOrderMenuDTO;
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
    private final EmailService emailService;

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

    private String buildOrderListHtml(PlaceOrderDTO order) {
        StringBuilder orderListHtml = new StringBuilder();

        for (CreateOrderMenuDTO orderMenu : order.orderMenus()) {
            orderListHtml.append(String.format("""
            <div class="order-item">
                <img src="%s" alt="%s">
                <span>%s (%d) </span>
                <span> - $%.0f</span>
            </div>
            """,
                    orderMenu.image(),  // URL de la imagen
                    orderMenu.menuName(),   // Alt text de la imagen
                    orderMenu.menuName(),   // Nombre del producto
                    orderMenu.quantity(),         // Cantidad del producto
                    orderMenu.price() * orderMenu.quantity()  // Precio total del producto
            ));
        }

        return orderListHtml.toString();
    }
    private String buildOrderEmailBody(PlaceOrderDTO order) {
        return String.format("""
                    <html>
                    <head>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                background-color: #f8f8f8;
                                margin: 0;
                                padding: 20px;
                            }
                            .container {
                                max-width: 600px;
                                background: white;
                                padding: 20px;
                                border-radius: 10px;
                                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
                                text-align: center;
                            }
                            h3 {
                                color: #ff6600;
                                margin-bottom: 20px;
                            }
                            .order-list {
                                background: #f2f2f2;
                                padding: 10px;
                                border-radius: 5px;
                                text-align: left;
                                display: inline-block;
                                width: 100%%;
                            }
                            .order-item {
                                display: flex;
                                align-items: center;
                                padding: 8px;
                                border-bottom: 1px solid #ddd;
                            }
                            .order-item img {
                                width: 40px;
                                height: 40px;
                                border-radius: 5px;
                                margin-right: 10px;
                            }
                            .order-item span {
                                font-size: 16px;
                                color: #333;
                            }
                            .footer {
                                margin-top: 20px;
                                font-size: 14px;
                                color: #777;
                            }
                        </style>
                        </head>
                        <body>
                            <div class="container">
                                <h3>RoadBites© - Pedido Confirmado</h3>
                                <p><strong>%s</strong>, tu pedido ha sido recibido con éxito:</p>
                                <div class="order-list">%s</div>
                                <p>¡Gracias por tu compra!</p>
                            </div>
                        </body>
                        </html>
                    """, order.userName(), buildOrderListHtml(order));

    }
}
