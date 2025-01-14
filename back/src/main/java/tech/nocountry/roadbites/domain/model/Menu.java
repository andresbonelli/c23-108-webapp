package tech.nocountry.roadbites.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "menu_category", nullable = false)
    private MenuCategory category;

    public Menu(String name, Double price, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
