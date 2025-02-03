package tech.nocountry.roadbites.controller.dto.order;

import tech.nocountry.roadbites.domain.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        String userName,
        String userEmail,
        LocalDateTime  orderDate,
        OrderStatus status,
        List<OrderMenuResponseDTO> orderMenus
) {
}
