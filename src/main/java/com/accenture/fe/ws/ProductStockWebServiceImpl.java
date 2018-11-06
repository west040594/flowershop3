package com.accenture.fe.ws;

import com.accenture.be.entity.product.Product;
import com.accenture.be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("productStockWebService")
public class ProductStockWebServiceImpl implements ProductStockWebService {

    @Autowired
    private ProductRepository productDAO;

    @Transactional
    @Override
    public void increaseProductStockSize(int count) {
        List<Product> products = (List<Product>) productDAO.findAll();
        for (Product product : products) {
            product.setInStock(product.getInStock() + count);
            productDAO.save(product);
        }
    }
}
