package com.accenture.be.business.product.interfaces;
import com.accenture.be.entity.product.Product;


import javax.ws.rs.Produces;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<Product> findAllProduct();
    List<Product> findProductByName(String productName);
    List<Product> findProductByRangePrice(BigDecimal min, BigDecimal max);
    Product getProductById(long productId);
    void changeProductQuantityInStock(Long productId, int quantity);
}
