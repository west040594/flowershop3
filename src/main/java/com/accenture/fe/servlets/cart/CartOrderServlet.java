package com.accenture.fe.servlets.cart;

import com.accenture.be.business.cart.Cart;
import com.accenture.be.business.customer.converters.CustomerConverter;
import com.accenture.be.business.order.converters.OrderConverter;
import com.accenture.be.business.order.exceptions.OrderException;
import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.be.entity.order.Order;
import com.accenture.fe.dto.customer.CustomerDTO;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.user.UserDTO;
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

@WebServlet(urlPatterns = "/cart")
public class CartOrderServlet extends HttpServlet {

    @Autowired
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null) {
            req.getRequestDispatcher("cart/cart.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null) {

            //Берем поля из формы, заполняем покупателя
            UserDTO userDTO = (UserDTO)session.getAttribute("user");
            CustomerDTO customerDTO = userDTO.getCustomer();
            customerDTO.setFirstName(req.getParameter("firstName"));
            customerDTO.setLastName(req.getParameter("lastName"));
            customerDTO.setPhoneNumber(req.getParameter("phone"));
            customerDTO.setStreet(req.getParameter("street"));
            customerDTO.setCity(req.getParameter("city"));
            customerDTO.setCountry(req.getParameter("country"));

            //формируем новый заказ DTO
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setCustomer(customerDTO);

            //Создаем новый заказ, при ошибки перезугружаем сраницу с списком errors
            try {
                orderDTO = OrderConverter.convertToDTO(orderService.createOrder(orderDTO));
            } catch (OrderException e) {
                req.setAttribute("error", e.getMessage());
                doGet(req, resp);
                return;
            }
            //Если ошибки не возникло очищаем козину и показываем новый заказ
            userDTO.getCustomer().getCart().removeAllItem();
            resp.sendRedirect("/orders/view?id="+orderDTO.getId());

        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
