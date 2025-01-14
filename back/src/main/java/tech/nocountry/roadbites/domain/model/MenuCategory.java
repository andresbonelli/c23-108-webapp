package tech.nocountry.roadbites.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "category", schema = "public")
@NoArgsConstructor
public class MenuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public MenuCategory(String name) {
        this.name = name;
    }
}