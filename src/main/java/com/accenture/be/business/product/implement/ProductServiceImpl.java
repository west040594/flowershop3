package com.accenture.be.business.product.implement;

import com.accenture.be.access.product.ProductDAO;
import com.accenture.be.business.product.interfaces.ProductService;
import com.accenture.be.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    @Override
    public List<Product> findAllProduct() {
        return productDAO.findAll();
    }

    @Override
    public Product getProductById(long productId) {
        return productDAO.findById(productId);
    }
}