package ru.akaleganov.container;

import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.service.Dispatch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class ImajeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Photo photo = Dispatch.getInstance().access("findByIdPhoto", new Photo(Integer.valueOf(req.getParameter("id"))));
        resp.setContentType("image/jpeg");
        resp.setContentLength(photo.getPhoto().length);
        resp.getOutputStream().write(photo.getPhoto());
//        resp.getOutputStream().write();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
