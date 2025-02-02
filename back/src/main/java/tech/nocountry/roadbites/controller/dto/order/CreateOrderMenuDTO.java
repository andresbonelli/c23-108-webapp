package tech.nocountry.roadbites.controller.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CreateOrderMenuDTO(
        @Schema(description = "Menu Id", example = "1")
        @NotNull(message = "Menu Id is required")
        Long menuId,
        @Schema(description = "Quantity", example = "2")
        @NotNull(message = "Quantity is required")
        Integer quantity
) {
}
