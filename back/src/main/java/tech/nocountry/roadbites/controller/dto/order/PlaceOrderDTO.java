package tech.nocountry.roadbites.controller.dto.order;

import java.util.List;

public record PlaceOrderDTO(
        String userName,
        List<CreateOrderMenuDTO> orderMenus
) {
}
