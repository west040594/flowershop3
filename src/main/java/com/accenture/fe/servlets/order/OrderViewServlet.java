package com.accenture.fe.servlets.order;

import com.accenture.be.business.order.converters.OrderConverter;
import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.fe.dto.order.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/orders/view")
public class OrderViewServlet extends HttpServlet {
    @Autowired
    private OrderService orderService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("id");
        OrderDTO orderDTO = null;

        if (orderId != null) {
            orderDTO = OrderConverter.convertToDTO(
                    orderService.getOrderById(Long.parseLong(orderId)));
        }

        if(orderDTO != null) {
            req.setAttribute("order", orderDTO);
            req.getRequestDispatcher("/order/view.jsp").forward(req,resp);

        }
        else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
