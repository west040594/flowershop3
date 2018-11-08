package com.accenture.fe.servlets.cart;

import com.accenture.be.business.order.exceptions.OrderException;
import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.fe.dto.customer.CustomerDTO;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.order.OrderForm;
import com.accenture.fe.dto.user.UserDTO;
import org.dozer.Mapper;
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

    @Autowired
    private Mapper mapper;

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
            OrderForm orderForm = new OrderForm(userDTO.getCustomer().getId(),
                    req.getParameter("firstName"), req.getParameter("lastName"),
                    req.getParameter("phone"), req.getParameter("street"),
                    req.getParameter("city"), req.getParameter("country"),
                    userDTO.getCustomer().getCart());

            //Создаем новый заказ, при ошибки перезугружаем сраницу с списком errors
            OrderDTO orderDTO = null;
            try {
                orderDTO = mapper.map(orderService.createOrder(orderForm), OrderDTO.class);;
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
