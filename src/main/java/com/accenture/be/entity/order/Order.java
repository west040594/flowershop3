package com.accenture.be.entity.order;


import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.orderproduct.OrderProduct;
import com.accenture.be.entity.product.Product;
import com.accenture.fe.enums.order.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_order")
@NamedQueries({
        @NamedQuery(name = "Order.findAll",
                query = "SELECT o FROM Order o"),

        @NamedQuery(name = "Order.findById",
                query = "SELECT o FROM Order o WHERE o.id = :id"),

        @NamedQuery(name = "Order.findByCustomer",
                query = "SELECT o FROM Order o WHERE o.customer = :customer"),
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "total", precision = 8, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", columnDefinition = "smallint")
    private OrderStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "closet_at")
    private Date closetAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "tb_order_product",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private List<Product> products;


    public Order() {
    }

    public Order(Customer customer, BigDecimal total, String deliveryAddress, OrderStatus status, Date createdAt, Date closetAt) {
        this.customer = customer;
        this.total = total;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.createdAt = createdAt;
        this.closetAt = closetAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getClosetAt() {
        return closetAt;
    }

    public void setClosetAt(Date closetAt) {
        this.closetAt = closetAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(createdAt, order.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, createdAt);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", total=" + total +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", closetAt=" + closetAt +
                '}';
    }
}
