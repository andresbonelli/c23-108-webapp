package tech.nocountry.roadbites.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_menu", schema = "public")
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public OrderMenu() {
    }

    public Long getId() {
        return this.id;
    }

    public Order getOrder() {
        return this.order;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderMenu)) return false;
        final OrderMenu other = (OrderMenu) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$order = this.getOrder();
        final Object other$order = other.getOrder();
        if (this$order == null ? other$order != null : !this$order.equals(other$order)) return false;
        final Object this$menu = this.getMenu();
        final Object other$menu = other.getMenu();
        if (this$menu == null ? other$menu != null : !this$menu.equals(other$menu)) return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderMenu;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $order = this.getOrder();
        result = result * PRIME + ($order == null ? 43 : $order.hashCode());
        final Object $menu = this.getMenu();
        result = result * PRIME + ($menu == null ? 43 : $menu.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        return result;
    }

    public String toString() {
        return "OrderMenu(id=" + this.getId() + ", order=" + this.getOrder() + ", menu=" + this.getMenu() + ", amount=" + this.getAmount() + ")";
    }
}
