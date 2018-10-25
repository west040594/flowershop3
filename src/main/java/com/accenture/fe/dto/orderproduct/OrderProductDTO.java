package com.accenture.fe.dto.orderproduct;

import com.accenture.fe.dto.customer.CustomerDTO;
import com.accenture.fe.dto.product.ProductDTO;

public class OrderProductDTO {
    private long id;
    private ProductDTO product;
    private CustomerDTO customer;
    private int quantity;

    public OrderProductDTO() {
    }

    public OrderProductDTO(long id, ProductDTO product, CustomerDTO customer, int quantity) {
        this.id = id;
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
