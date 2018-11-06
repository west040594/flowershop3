package com.accenture.fe.servlets.order;

import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.fe.dto.order.OrderDTO;
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

@WebServlet(urlPatterns = "/orders/view")
public class OrderViewServlet extends HttpServlet {

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
        String orderId = req.getParameter("id");
        OrderDTO orderDTO = null;
        //Если гет параметр на заказ присутствует формируем заказ
        if (orderId != null) {
            orderDTO = mapper.map(orderService.getOrderById(Long.parseLong(orderId)), OrderDTO.class);
        }
        if(orderDTO != null) {
            req.setAttribute("order", orderDTO);
            req.getRequestDispatcher("/order/view.jsp").forward(req,resp);
        }
        else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null && req.getParameter("orderId") != null ) {
            //Берем пользователя из сессии, id заказа и оформляем заказ
            UserDTO userDTO = (UserDTO)session.getAttribute("user");
            String orderId = req.getParameter("orderId");
            OrderDTO orderDTO = mapper.map(
                            orderService.changerOrderStatusToPaid(Long.parseLong(orderId)), OrderDTO.class);
            //Изменяем баланс у пользователя в сессии
            userDTO.getCustomer().setBalance(orderDTO.getCustomer().getBalance());
            resp.sendRedirect("/products/index");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
