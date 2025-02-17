package tech.nocountry.roadbites.controller.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateOrderMenuDTO(
        @Schema(description = "Menu Id", example = "1")
        @NotNull(message = "Menu Id is required")
        Long menuId,
        @Schema(description = "Menu Name", example = "Arroz con Pollo")
        @NotNull(message = "Menu Name is required")
        String menuName,
        @Schema(description = "Menu image", example = "https://images.unsplash.com/photo-1603133872878-684f208fb84b?q=80&w=300")
        String image,
        @Schema(description = "Menu price", example = "12.50")
        @NotNull(message = "Menu price is required")
        @Positive(message = "Menu price must be a positive decimal")
        Double price,
        @Schema(description = "Quantity", example = "1")
        @NotNull(message = "Quantity is required")
        Integer quantity

) {
}
