package tech.nocountry.roadbites.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.nocountry.roadbites.controller.dto.MenuResponseDTO;
import tech.nocountry.roadbites.domain.repository.MenuRepository;
import tech.nocountry.roadbites.domain.model.MenuCategory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuResponseDTO> getAllMenus() {
        return menuRepository.findAll()
                .stream()
                .map( menu -> new MenuResponseDTO(
                        menu.getId(),
                        menu.getName(),
                        menu.getPrice(),
                        menu.getDescription(),
                        menu.getImage(),
                        menu.getCategory()
                )).toList();
    }

    public List<MenuResponseDTO> getMenuByCategory(String category) {
        return menuRepository.findByCategory(category)
                .stream()
                .map( menu -> new MenuResponseDTO(
                        menu.getId(),
                        menu.getName(),
                        menu.getPrice(),
                        menu.getDescription(),
                        menu.getImage(),
                        menu.getCategory()
                )).toList();
    }

}
