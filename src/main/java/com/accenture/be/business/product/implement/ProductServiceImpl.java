package com.accenture.be.business.product.implement;

import com.accenture.be.access.product.ProductDAO;
import com.accenture.be.business.product.converters.ProductConverter;
import com.accenture.be.business.product.interfaces.ProductService;
import com.accenture.be.entity.order.Order;
import com.accenture.be.entity.product.Product;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    @Override
    public List<Product> findAllProduct() {

        List<Product> products =  productDAO.findAll();
        //Возращаем цветы которых на складе больше 0
        return  products.stream().filter(product -> product.getInStock() > 0)
                .collect(Collectors.toList());

    }

    @Override
    public Product getProductById(long productId) {
        return productDAO.findById(productId);
    }


    @Override
    public void changeProductQuantityInStock(Long productId, int quantity) {
        Product product = productDAO.findById(productId);
        int inStock = product.getInStock();
        if(inStock > quantity) {
            product.setInStock(inStock - quantity);
        }
        productDAO.update(product);
    }
}
