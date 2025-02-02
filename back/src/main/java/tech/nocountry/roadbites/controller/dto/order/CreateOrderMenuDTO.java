package tech.nocountry.roadbites.controller.dto.order;

public record CreateOrderMenuDTO(
        Long menuId,
        Integer quantity
) {
}
