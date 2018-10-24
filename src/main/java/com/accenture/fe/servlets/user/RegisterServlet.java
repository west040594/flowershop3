package com.accenture.fe.servlets.user;

import com.accenture.be.business.user.converters.UserConverter;
import com.accenture.be.business.user.exceptions.UserException;
import com.accenture.be.business.user.interfaces.UserService;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.customer.CustomerDTO;
import com.accenture.fe.dto.user.UserDTO;
import com.accenture.fe.enums.user.UserRole;
import com.accenture.fe.enums.user.UserStatus;
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
import java.math.BigDecimal;
import java.util.Date;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        //Формируем Пользователя и соотвествующего ему Покупателя
        //Покупателю назначается стартовый бонус в размере 2000 и скидка 0%
        CustomerDTO customerDTO = new CustomerDTO(req.getParameter("firstName"), req.getParameter("lastName"),
                new BigDecimal(2000), 0);
        UserDTO userDTO = new UserDTO(
                req.getParameter("username"), req.getParameter("password"), req.getParameter("email") );

        userDTO.setConfirmPassword(req.getParameter("confirmPassword"));
        userDTO.setStatus(UserStatus.ACTIVE);
        userDTO.setRole(UserRole.USER);
        userDTO.setCreatedUpdated(new Date(), new Date());
        userDTO.setCustomer(customerDTO);

        User user = null;
        try {
            user = userService.register(userDTO);
        } catch (UserException e) {
            req.setAttribute("error", e.getMessage());
        }

        //Если пользователь зарегестрирован то сохраняем его в сессию и делаем редирект
        if(user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", UserConverter.convertToDTO(user));
            resp.sendRedirect("/products/index");
        //Иначе перезагружаем страницу и выводим ошибки
        } else {
            doGet(req, resp);
        }

    }
}
