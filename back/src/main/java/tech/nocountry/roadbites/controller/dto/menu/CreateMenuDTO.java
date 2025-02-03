package tech.nocountry.roadbites.controller.dto.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateMenuDTO(
        @Schema(description = "Menu name", example = "Hamburguesa")
        @NotBlank(message = "Menu name is mandatory")
        String name,
        @Schema(description = "Menu description", example = "Hamburguesa con queso, lechuga y tomate")
        @NotBlank(message = "Menu description is mandatory")
        String description,
        @Schema(description = "Menu image (optional)", example = "https://www.example.com/uploads/Hamburguesa.jpg")
        String image,
        @Schema(description = "Menu price", example = "10.00")
        @NotBlank(message = "Menu price is mandatory")
        @Positive(message = "Menu price must be a positive decimal")
        Double price,
        @Schema(description = "Menu category ID", example = "1")
        @NotBlank(message = "Menu category ID is mandatory")
        @Min(value = 1, message = "Menu category ID must be a positive integer")
        Long menuCategoryId
) {
}
