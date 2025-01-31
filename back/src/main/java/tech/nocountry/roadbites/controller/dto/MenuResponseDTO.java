package tech.nocountry.roadbites.controller.dto;

import tech.nocountry.roadbites.domain.model.MenuCategory;

public record MenuResponseDTO(
        Long id,
        String name,
        Double price,
        String description,
        String image,
        MenuCategory category
) {
}
