package tech.nocountry.roadbites.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.nocountry.roadbites.controller.dto.menu.MenuResponseDTO;
import tech.nocountry.roadbites.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MenuResponseDTO> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuResponseDTO> getMenuByCategory(@PathVariable String category) {
        return menuService.getMenuByCategory(category);
    }

}
