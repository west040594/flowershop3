package com.accenture.fe.servlets.order;

import com.accenture.be.business.order.exceptions.OrderException;
import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.user.UserDTO;
import com.accenture.fe.enums.user.UserRole;
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
        UserDTO userDTOSession = (UserDTO) req.getSession().getAttribute("user");
        String orderId = req.getParameter("id");
        OrderDTO orderDTO = null;
        //Если гет параметр на заказ присутствует формируем заказ
        //Если заказ не найден, то отправляем страницу ошибки - заказ не найден
        if (orderId != null) {
            try {
                orderDTO = mapper.map(orderService.getOrderById(Long.parseLong(orderId)), OrderDTO.class);
            } catch (OrderException e) {
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
                return;
            }
        }
        //Если заказ существует и заказ принадлежит авторизированому пользователю или это админ
        //То показываем заказ, иначе выдаем ошибку
        if(orderDTO != null &&
                (orderService.orderBelongsToUser(orderDTO.getId(), userDTOSession.getId()))
                || userDTOSession.getRole().equals(UserRole.ADMIN)
        ) {
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
            OrderDTO orderDTO = null;

            //При ошибки перезугружаем сраницу с списком errors
            try {
                orderDTO = mapper.map(
                                orderService.changerOrderStatusToPaid(Long.parseLong(orderId)), OrderDTO.class);
            } catch (OrderException e) {
                req.getSession().setAttribute("orderError", e.getMessage());
                resp.sendRedirect("/orders/view?id="+orderId);
                return;
            }
            //Если ошибок не возникло
            //Изменяем баланс у пользователя в сессии и делаем редирект на страницу продуктов
            userDTO.getCustomer().setBalance(orderDTO.getCustomer().getBalance());
            resp.sendRedirect("/products/index");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
