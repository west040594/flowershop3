package com.accenture.fe.servlets.product;

import com.accenture.be.business.product.interfaces.ProductService;
import com.accenture.fe.dto.product.ProductDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = "/products/index")
public class ProductIndexServlet extends HttpServlet {

    @Autowired
    private ProductService productService;

    @Autowired
    private Mapper mapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDTO> productDTOS = null;
        if(req.getParameter("productname") != null) {
            String productName = new String(req.getParameter("productname").getBytes("iso-8859-1"),
                    "UTF-8");
            req.setAttribute("searchProductName", productName);
            productDTOS  = productService.findProductByName(productName).stream().
                    map(product -> mapper.map(product, ProductDTO.class))
                    .collect(Collectors.toList());

        } else if(req.getParameter("minPrice") != null && req.getParameter("minPrice") != null) {
            productDTOS  = productService.findProductByRangePrice(
                    new BigDecimal(req.getParameter("minPrice")),
                    new BigDecimal(req.getParameter("maxPrice"))).stream().
                    map(product -> mapper.map(product, ProductDTO.class))
                    .collect(Collectors.toList());

        } else {
            productDTOS  = productService.findAllProduct().stream().
                    map(product -> mapper.map(product, ProductDTO.class))
                    .collect(Collectors.toList());
        }
        req.setAttribute("products", productDTOS);
        req.getRequestDispatcher("/product/index.jsp").forward(req,resp);
    }
}
