package tech.nocountry.roadbites.controller.dto;

import java.util.List;

public record PlaceOrderDTO(
        Long userId,
        List<CreateOrderMenuDTO> orderMenus
) {
}
