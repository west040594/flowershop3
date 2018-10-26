package com.accenture.be.business.product.interfaces;

import com.accenture.be.entity.order.Order;
import com.accenture.be.entity.product.Product;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.product.ProductDTO;

import java.util.List;

public interface ProductService {
    List<Product> findAllProduct();
    Product getProductById(long productId);
    List<ProductDTO> getProductsByOrder(OrderDTO order);
}
