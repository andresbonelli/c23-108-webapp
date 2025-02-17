package tech.nocountry.roadbites.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.nocountry.roadbites.controller.dto.menu.MenuResponseDTO;
import tech.nocountry.roadbites.domain.model.Menu;
import tech.nocountry.roadbites.domain.repository.MenuRepository;
import tech.nocountry.roadbites.service.exceptions.MenuNotFoundException;

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
                        menu.getCategory().getName()
                )).toList();
    }

    public List<MenuResponseDTO> getMenuByCategory(String category) {
        return menuRepository.findByCategoryName(category)
                .stream()
                .map( menu -> new MenuResponseDTO(
                        menu.getId(),
                        menu.getName(),
                        menu.getPrice(),
                        menu.getDescription(),
                        menu.getImage(),
                        menu.getCategory().getName()
                )).toList();
    }

    public Menu getMenuById(Long id) {
        return menuRepository.findById(id).orElseThrow(MenuNotFoundException::new);
    }

}
