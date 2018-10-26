package com.accenture.fe.servlets.order;

import com.accenture.be.business.order.converters.OrderConverter;
import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.be.business.orderproduct.interfaces.OrderProductService;
import com.accenture.be.business.product.interfaces.ProductService;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.orderproduct.OrderProductDTO;
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

@WebServlet(urlPatterns = "/orders/view")
public class OrderViewServlet extends HttpServlet {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProductService orderProductService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("id");
        OrderDTO orderDTO = null;

        if (orderId != null) {
            orderDTO = orderService.getOrderById(Long.parseLong(orderId));
            List<OrderProductDTO> orderProductDTOS = orderProductService.findOrderProductByOrder(orderDTO);
            orderDTO.setOrderProducts(orderProductDTOS);
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
        String orderId = req.getParameter("orderId");
        orderService.changerOrderStatusToPaid(Long.parseLong(orderId));
        resp.sendRedirect("/products/index");
    }
}
