package com.accenture.fe.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private boolean registered = false;

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
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        /*User user = new User(username, email);
        userAccessService.registerUser(user);*/
        req.setAttribute("registered", registered);
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }
}
