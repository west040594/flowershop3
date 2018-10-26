package com.accenture.fe.servlets.cart;

import com.accenture.fe.dto.cart.AjaxDataCart;
import com.accenture.be.business.cart.Cart;
import com.accenture.be.business.cart.CartItem;
import com.accenture.be.business.product.converters.ProductConverter;
import com.accenture.be.business.product.interfaces.ProductService;
import com.accenture.fe.dto.product.ProductDTO;
import com.accenture.fe.dto.user.UserDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/addCartItem")
public class AddCartItemServlet extends HttpServlet {

    @Autowired
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null) {
            //Берем данные с ajax запроса(id продукта) и добавляем его в корзину
            Long productId = Long.parseLong(req.getParameter("productId"));
            Cart cart = (Cart)((UserDTO)session.getAttribute("user")).getCustomer().getCart();
            ProductDTO productDTO = ProductConverter.convertToDTO(productService.getProductById(productId));
            CartItem cartItem = new CartItem(productDTO, 1);
            cart.addItem(cartItem);
            //Отправляем json с данными корзины

            String json = new Gson().toJson(new AjaxDataCart(cart.getTotalRub(), cart.getItemCount()));
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        }
    }
}
