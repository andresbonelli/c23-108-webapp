package tech.nocountry.roadbites.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_menu", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu menu;

    private Integer amount;

    public OrderMenu(Order order, Menu menu, Integer amount) {
        this.order = order;
        this.menu = menu;
        this.amount = amount;
    }
}
