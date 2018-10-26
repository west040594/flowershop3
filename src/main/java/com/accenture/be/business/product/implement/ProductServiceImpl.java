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

    @Override
    public List<ProductDTO> getProductsByOrder(OrderDTO order) {
        List<Product> products = productDAO.findByOrder(order.getId());
        return ProductConverter.convertToDTO(products);
    }
}
