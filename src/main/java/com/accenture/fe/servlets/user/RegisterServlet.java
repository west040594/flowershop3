package com.accenture.fe.servlets.user;

import com.accenture.be.business.user.exceptions.UserException;
import com.accenture.be.business.user.interfaces.UserService;
import com.accenture.fe.dto.customer.CustomerDTO;
import com.accenture.fe.dto.user.RegisterForm;
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
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

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
        //Формируем форму регистрации
        RegisterForm registerForm = new RegisterForm(
                req.getParameter("firstName"), req.getParameter("lastName"),
                req.getParameter("username"), req.getParameter("email"),
                req.getParameter("password"), req.getParameter("confirmPassword"));

        //Регистрируем пользователя,при ошибки перезугружаем сраницу с списком errors
        UserDTO userDTO = null;
        try {
            userDTO = mapper.map(userService.register(registerForm), UserDTO.class);
        } catch (UserException e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
            return;
        }
        //Если ошибок не возникло устанавливаем пользователя в сессию
        userService.setUserSession(req.getSession(), userDTO);
        resp.sendRedirect("/products/index");

    }
}
