package com.accenture.be.entity.product;

import com.accenture.be.entity.category.Category;
import com.accenture.be.entity.order.Order;
import com.accenture.fe.enums.product.ProductStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_product")
@NamedQueries({
        @NamedQuery(name = "Product.findAll",
                query = "SELECT p FROM Product p"),

        @NamedQuery(name = "Product.findById",
                query = "SELECT p FROM Product p WHERE p.id = :id"),

        @NamedQuery(name = "Product.findByName",
                query = "SELECT p FROM Product p WHERE p.name = :name"),

        @NamedQuery(name = "Product.findByCategory",
                query = "SELECT p FROM Product p WHERE p.category.name = :category"),

        @NamedQuery(name = "Product.findByOrder",
                query = " SELECT p FROM Product p JOIN p.orders o WHERE o.id = :orderId"),



})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", precision = 6, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", columnDefinition = "smallint")
    private ProductStatus status;

    @Column(name = "in_stock")
    private int inStock;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    public Product() {
    }

    public Product(long id, String name, BigDecimal price, String description, String imageUrl, ProductStatus status, int inStock, Date createdAt, Date updatedAt, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = status;
        this.inStock = inStock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", status=" + status +
                ", inStock=" + inStock +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", category=" + category +
                '}';
    }
}
