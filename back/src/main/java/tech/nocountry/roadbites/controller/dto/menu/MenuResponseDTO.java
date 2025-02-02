package tech.nocountry.roadbites.controller.dto.menu;

public record MenuResponseDTO(
        Long id,
        String name,
        Double price,
        String description,
        String image,
        String category
) {
}
