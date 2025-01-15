package tech.nocountry.roadbites.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.nocountry.roadbites.domain.model.MenuCategory;

@Repository
public interface CategoryRepository extends JpaRepository<MenuCategory, Long> {
    MenuCategory findByName(String name);
}
