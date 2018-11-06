package com.accenture.fe.dto.orderproduct;

import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.product.ProductDTO;
import org.dozer.Mapping;

public class OrderProductDTO {

    private long id;
    private ProductDTO product;
    @Mapping("this")
    private OrderDTO order;
    private int quantity;

    public OrderProductDTO() {
    }

    public OrderProductDTO(long id, ProductDTO product, OrderDTO order, int quantity) {
        this.id = id;
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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
