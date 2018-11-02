package com.accenture.fe.servlets.order;

import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.be.business.user.converters.UserConverter;
import com.accenture.be.business.user.interfaces.UserService;
import com.accenture.be.entity.order.Order;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.user.UserDTO;
import com.accenture.fe.enums.user.UserRole;
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
import java.util.List;

@WebServlet(urlPatterns = "/orders/index")
public class OrderIndexServlet extends HttpServlet {

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
            UserDTO sessionUser = (UserDTO)session.getAttribute("user");
            if(sessionUser.getRole() == UserRole.ADMIN) {
                List<OrderDTO> orders = orderService.findAllOrder();
                req.setAttribute("orders", orders);
                req.getRequestDispatcher("/order/index.jsp").forward(req,resp);
                return;
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
