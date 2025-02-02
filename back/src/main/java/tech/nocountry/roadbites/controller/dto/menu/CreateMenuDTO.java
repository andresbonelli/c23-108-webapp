package tech.nocountry.roadbites.controller.dto.menu;

public record CreateMenuDTO(
        String name,
        String description,
        String image,
        Double price,
        Long menuCategoryId
) {
}
