package com.accenture.fe.servlets;

import com.accenture.be.business.product.ProductConverter;
import com.accenture.be.business.product.ProductService;
import com.accenture.be.entity.product.Product;
import com.accenture.fe.dto.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductsServlet extends HttpServlet {

    @Autowired
    private ProductService productService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = productService.findAllProduct();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = ProductConverter.convertToDTO(product);
            if(productDTO != null) {
                productDTOS.add(productDTO);
            }
        }

        req.setAttribute("products", productDTOS);
        req.getRequestDispatcher("/products.jsp").forward(req,resp);
    }
}
