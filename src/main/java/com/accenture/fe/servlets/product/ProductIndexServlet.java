package com.accenture.fe.servlets.product;

import com.accenture.be.business.product.converters.ProductConverter;
import com.accenture.be.business.product.interfaces.ProductService;
import com.accenture.fe.dto.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/products/index")
public class ProductIndexServlet extends HttpServlet {

    @Autowired
    private ProductService productService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ProductDTO> productDTOS = ProductConverter.convertToDto(productService.findAllProduct());
        req.setAttribute("products", productDTOS);
        req.getRequestDispatcher("/product/index.jsp").forward(req,resp);
    }
}
