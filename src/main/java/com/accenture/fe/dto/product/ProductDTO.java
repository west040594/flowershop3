package com.accenture.fe.dto.product;

import com.accenture.fe.dto.category.CategoryDTO;
import com.accenture.fe.enums.product.ProductStatus;

import java.math.BigDecimal;
import java.util.Date;

public class ProductDTO {

    private long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private ProductStatus status;
    private int inStock;
    private Date createdAt;
    private Date updatedAt;
    private CategoryDTO category;

    public ProductDTO() {
    }

    public ProductDTO(long id, String name, BigDecimal price, String description, String imageUrl, ProductStatus status, int inStock, Date createdAt, Date updatedAt, CategoryDTO category) {
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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
