package com.accenture.fe.servlets.helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/image")
public class ImageServlet extends HttpServlet {

    private String imagePath;


    @Override
    public void init() throws ServletException {
        this.imagePath = "/images";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpg");

        String imageName = req.getParameter("img");
        String imageFolder = req.getParameter("type");

        if(imageName != null && imageFolder != null) {
            ServletContext sc = getServletContext();
            InputStream is = sc.getResourceAsStream("images/" + imageFolder + "/" + imageName);

            BufferedImage bi = ImageIO.read(is);
            OutputStream os = resp.getOutputStream();
            ImageIO.write(bi, "jpg", os);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
