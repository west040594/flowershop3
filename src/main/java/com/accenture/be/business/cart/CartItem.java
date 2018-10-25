package com.accenture.be.business.cart;


import com.accenture.fe.dto.product.ProductDTO;

import java.math.BigDecimal;

public class CartItem {

    public ProductDTO product;
    private int quantity;

    public CartItem(ProductDTO product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCartItemTotal() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}
