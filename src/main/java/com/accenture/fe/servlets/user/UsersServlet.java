package com.accenture.fe.servlets;

import com.accenture.be.business.user.converters.UserConverter;
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
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/users")
public class UsersServlet extends HttpServlet {

    @Autowired
    private UserService userService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = userService.findAllUser();
        List<UserDTO> userDTOS = UserConverter.convertToDTO(users);

        req.setAttribute("users", userDTOS);
        req.getRequestDispatcher("/users.jsp").forward(req,resp);
    }
}
