package tech.nocountry.roadbites.domain.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "orders", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    private LocalDateTime orderDate;

    public Order(User user) {
        this.user = user;
        this.orderDate = LocalDateTime.now();
    }
}
