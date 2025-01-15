package tech.nocountry.roadbites.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "menu", schema = "public")
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

    public Menu() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public MenuCategory getCategory() {
        return this.category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(MenuCategory category) {
        this.category = category;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Menu other)) return false;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$category = this.getCategory();
        final Object other$category = other.getCategory();
        if (this$category == null ? other$category != null : !this$category.equals(other$category)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Menu;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        return result;
    }

    public String toString() {
        return "Menu(id=" + this.getId() + ", name=" + this.getName() + ", price=" + this.getPrice() + ", category=" + this.getCategory() + ")";
    }
}
