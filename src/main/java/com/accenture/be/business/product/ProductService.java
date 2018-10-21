package com.accenture.be.business.product;

import com.accenture.be.entity.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProduct();
    Product getProductById(long productId);
}
