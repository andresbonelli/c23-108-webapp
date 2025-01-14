package tech.nocountry.roadbites.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.nocountry.roadbites.domain.model.Menu;
import tech.nocountry.roadbites.domain.model.MenuCategory;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByCategory(MenuCategory category);
}
