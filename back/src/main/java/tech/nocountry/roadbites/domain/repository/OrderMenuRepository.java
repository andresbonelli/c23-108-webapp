package tech.nocountry.roadbites.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.nocountry.roadbites.domain.model.Menu;
import tech.nocountry.roadbites.domain.model.Order;
import tech.nocountry.roadbites.domain.model.OrderMenu;

import java.util.List;

@Repository
public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
    List<OrderMenu> findByOrder(Order order);
    List<OrderMenu> findByMenu(Menu menu);
}
