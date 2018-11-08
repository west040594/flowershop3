package com.accenture.fe.servlets.product;

import com.accenture.be.business.category.interfaces.CategoryService;
import com.accenture.be.business.product.filter.ProductFilter;
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
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = "/products/index")
public class ProductIndexServlet extends HttpServlet {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private Mapper mapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductFilter productFilter = new ProductFilter();
        productFilter.setName(req.getParameter("productname"));
        productFilter.setMinPrice(req.getParameter("minPrice"));
        productFilter.setMaxPrice(req.getParameter("maxPrice"));
        productFilter.setCategoryId(req.getParameter("category"));
        req.setAttribute("categoryList", categoryService.findAllCategory());
        req.setAttribute("selectedCategory", req.getParameter("category"));
        List<ProductDTO> productDTOS  = productService.findAllProduct(productFilter.getPredicate()).stream().
                map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        req.setAttribute("products", productDTOS);
        req.getRequestDispatcher("/product/index.jsp").forward(req,resp);
    }
}
