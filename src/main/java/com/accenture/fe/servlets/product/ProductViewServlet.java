package com.accenture.fe.servlets.product;

import com.accenture.be.business.product.ProductConverter;
import com.accenture.be.business.product.ProductService;
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

import static java.lang.Long.parseLong;

@WebServlet(urlPatterns = "/products/view")
public class ProductViewServlet extends HttpServlet {

    @Autowired
    private ProductService productService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");
        ProductDTO productDTO = null;

        if (productId != null) {
            productDTO = ProductConverter.convertToDTO(
                    productService.getProductById(Long.parseLong(productId)));
        }

        if(productDTO != null) {
            req.setAttribute("product", productDTO);
            req.getRequestDispatcher("/product/view.jsp").forward(req,resp);

        }
        else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
