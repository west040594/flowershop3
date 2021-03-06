package com.accenture.be.entity.orderproduct;

import com.accenture.be.entity.order.Order;
import com.accenture.be.entity.product.Product;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность для таблицы order_product
 */
@Entity
@Table(name = "tb_order_product")
@NamedQueries({
        @NamedQuery(name = "OrderProduct.findAll",
                query = "SELECT op FROM OrderProduct op"),

        @NamedQuery(name = "OrderProduct.findById",
                query = "SELECT op FROM OrderProduct op WHERE op.id = :id"),

        /*@NamedQuery(name = "OrderProduct.findByOrder",
                query = "SELECT op FROM OrderProduct op WHERE op.order.id = :orderId"),*/
})
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public OrderProduct() {
    }

    public OrderProduct(Product product, Order order, int quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return id == that.id &&
                Objects.equals(product, that.product) &&
                Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, order);
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", product=" + product +
                ", order=" + order +
                ", quantity=" + quantity +
                '}';
    }
}
