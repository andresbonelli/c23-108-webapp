package tech.nocountry.roadbites.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.nocountry.roadbites.domain.model.Order;
import tech.nocountry.roadbites.domain.model.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
