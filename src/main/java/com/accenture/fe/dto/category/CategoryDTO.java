package com.accenture.fe.dto.category;

import com.accenture.fe.dto.product.ProductDTO;
import com.accenture.fe.enums.category.CategoryStatus;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {
    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private CategoryStatus status;
    private List<ProductDTO> products = new ArrayList<>();

    public CategoryDTO() {
    }

    public CategoryDTO(long id, String name, String description, String imageUrl, CategoryStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = status;
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

    public CategoryStatus getStatus() {
        return status;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
