package tech.nocountry.roadbites.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String image;
    @ManyToOne
    @JoinColumn(name = "menu_category_id", nullable = false)
    private MenuCategory category;

}
