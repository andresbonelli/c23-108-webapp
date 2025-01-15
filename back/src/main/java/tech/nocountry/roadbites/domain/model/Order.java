package tech.nocountry.roadbites.domain.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "orders", schema = "public")
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

    public Order() {
    }

    public Long getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Order)) return false;
        final Order other = (Order) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final Object this$orderDate = this.getOrderDate();
        final Object other$orderDate = other.getOrderDate();
        if (this$orderDate == null ? other$orderDate != null : !this$orderDate.equals(other$orderDate)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Order;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final Object $orderDate = this.getOrderDate();
        result = result * PRIME + ($orderDate == null ? 43 : $orderDate.hashCode());
        return result;
    }

    public String toString() {
        return "Order(id=" + this.getId() + ", user=" + this.getUser() + ", orderDate=" + this.getOrderDate() + ")";
    }
}
