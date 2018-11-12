package com.accenture.be.business.product.implement;

import com.accenture.be.business.order.exceptions.OrderException;
import com.accenture.be.business.product.exceptions.ProductException;
import com.accenture.be.repository.ProductRepository;
import com.accenture.be.business.product.interfaces.ProductService;
import com.accenture.be.entity.product.Product;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productDAO;

    @Override
    public List<Product> findAllProduct() {

        List<Product> products = (List<Product>) productDAO.findAll();
        //Возращаем цветы которых на складе больше 0
        return  products.stream().filter(product -> product.getInStock() > 0)
                .collect(Collectors.toList());

    }

    @Override
    public List<Product> findAllProduct(Predicate predicate) {
        List<Product> products = (List<Product>) productDAO.findAll(predicate);
        return  products.stream().filter(product -> product.getInStock() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductByName(String productName) {
        List<Product> products = productDAO.findByNameContainingIgnoreCase(productName);
        return  products.stream().filter(product -> product.getInStock() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductByRangePrice(BigDecimal min, BigDecimal max) {
        List<Product> products = productDAO.findByPriceBetween(min, max);
        return  products.stream().filter(product -> product.getInStock() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(long productId) {
        return productDAO.findById(productId).get();
    }


    @Override
    public void changeProductQuantityInStock(Long productId, int quantity) throws ProductException {
        Product product = productDAO.findById(productId).get();
        int inStock = product.getInStock();
        if(inStock > quantity) {
            product.setInStock(inStock - quantity);
        } else {
            throw new ProductException("Требуемого количества " + quantity + "." +
                    "Продукта: " + product.getName() + " нету в наличии на складе. Подождите поступления.");
        }
        productDAO.save(product);
    }
}
