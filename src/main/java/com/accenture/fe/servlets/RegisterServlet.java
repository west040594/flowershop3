package com.accenture.fe.servlets;

import com.accenture.be.business.user.UserRegisterService;
import com.accenture.be.business.user.UserService;
import com.accenture.be.entity.user.User;
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

    private boolean registered = false;

    @Autowired
    private UserRegisterService userRegisterService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        registered = false;
        req.setAttribute("registered", registered);
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        registered = true;
        req.setCharacterEncoding("utf8");

        User user = userRegisterService.register(
                req.getParameter("firstName"), req.getParameter("lastName"),
                req.getParameter("username"), req.getParameter("email"),
                req.getParameter("password"), req.getParameter("passwordConfirm"));


        if(user != null) {
            resp.sendRedirect("/index");
            return;
        }

        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }
}
