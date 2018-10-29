package com.accenture.fe.servlets.order;

import com.accenture.be.business.order.converters.OrderConverter;
import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.be.business.orderproduct.converters.OrderProductConverter;
import com.accenture.be.business.orderproduct.interfaces.OrderProductService;
import com.accenture.be.business.product.interfaces.ProductService;
import com.accenture.be.business.user.converters.UserConverter;
import com.accenture.be.entity.order.Order;
import com.accenture.be.entity.orderproduct.OrderProduct;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.orderproduct.OrderProductDTO;
import com.accenture.fe.dto.product.ProductDTO;
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
import java.util.List;

@WebServlet(urlPatterns = "/orders/view")
public class OrderViewServlet extends HttpServlet {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderProductService orderProductService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("id");
        Order order = null;
        List<OrderProduct> orderProducts = null;

        //Если гет параметр на заказ присутствует формируем заказа и его предметы
        if (orderId != null) {
            order = orderService.getOrderById(Long.parseLong(orderId));
            orderProducts = orderProductService.findOrderProductByOrder(order);
        }

        //Конвертируем заказ и его предметы в DTO и одаем в jsp
        if(order != null && orderProducts != null) {
            OrderDTO orderDTO = OrderConverter.convertToDTO(order);
            orderDTO.setOrderProducts(OrderProductConverter.convertToDTO(orderProducts));
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
            orderService.changerOrderStatusToPaid(userDTO, Long.parseLong(orderId));
            resp.sendRedirect("/products/index");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
