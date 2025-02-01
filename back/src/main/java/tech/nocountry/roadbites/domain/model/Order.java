package tech.nocountry.roadbites.domain.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    private LocalDateTime orderDate;
}
