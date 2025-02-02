package tech.nocountry.roadbites;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tech.nocountry.roadbites.controller.dto.menu.CreateCategoryDTO;
import tech.nocountry.roadbites.controller.dto.menu.CreateMenuDTO;
import tech.nocountry.roadbites.domain.model.Menu;
import tech.nocountry.roadbites.domain.model.MenuCategory;
import tech.nocountry.roadbites.domain.repository.CategoryRepository;
import tech.nocountry.roadbites.domain.repository.MenuRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SampleDataLoader.class);
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            log.info("Loading sample data into Category table");
            try  {
                ClassPathResource resource = new ClassPathResource("data/sample-categories.json");
                if (!resource.exists()) {
                    throw new RuntimeException("Sample data file not found");
                }
                List<CreateCategoryDTO> categoryItems = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
                for(CreateCategoryDTO categoryItem : categoryItems) {
                    MenuCategory category = MenuCategory.builder().name(categoryItem.name()).build();
                    categoryRepository.save(category);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error loading sample Categories", e);
            }
        }
        if (menuRepository.count() == 0) {
            log.info("Loading sample data into Menu table");
            try  {
                ClassPathResource resource = new ClassPathResource("data/sample-menus.json");
                if (!resource.exists()) {
                    throw new RuntimeException("Sample data file not found");
                }
                List<CreateMenuDTO> menuItems = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
                for(CreateMenuDTO menuItem : menuItems) {
                    MenuCategory category = categoryRepository.findById(menuItem.menuCategoryId())
                            .orElseThrow(() -> new RuntimeException("Category not found for ID: " + menuItem.menuCategoryId()));
                    Menu menu = Menu.builder()
                            .name(menuItem.name())
                            .price(menuItem.price())
                            .description(menuItem.description())
                            .image(menuItem.image())
                            .category(category)
                            .build();
                    menuRepository.save(menu);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error loading sample menus", e);
            }
        } else {
            log.info("Employees table is not empty");
        }

    }

}
