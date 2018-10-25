package com.accenture.fe.servlets.user;

import com.accenture.be.business.cart.Cart;
import com.accenture.be.business.user.converters.UserConverter;
import com.accenture.be.business.user.exceptions.UserException;
import com.accenture.be.business.user.interfaces.UserService;
import com.accenture.be.entity.user.User;
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

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("user/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = new UserDTO(
                req.getParameter("username"), req.getParameter("password"), req.getParameter("username") );

        //Авторизируем пользователя
        try {
            userDTO = userService.login(userDTO);
        } catch (UserException e) {
            req.setAttribute("error", e.getMessage());
        }

        //Если пользователь зарегестрирован то сохраняем его в сессию и делаем редирект
        if(userDTO != null) {
            userService.setUserSession(req.getSession(), userDTO);
            resp.sendRedirect("/products/index");
            //Иначе перезагружаем страницу и выводим ошибки
        } else {
            doGet(req, resp);
        }
    }
}
