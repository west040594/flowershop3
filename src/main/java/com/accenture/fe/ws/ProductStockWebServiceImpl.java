package com.accenture.fe.ws;

import com.accenture.be.access.product.ProductDAO;
import com.accenture.be.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("productStockWebService")
public class ProductStockWebServiceImpl implements ProductStockWebService {

    @Autowired
    private ProductDAO productDAO;

    @Transactional
    @Override
    public void increaseProductStockSize(int count) {
        List<Product> products = productDAO.findAll();
        for (Product product : products) {
            product.setInStock(product.getInStock() + count);
            productDAO.update(product);
        }
    }
}
