package ru.akaleganov.container;

import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.modelsannot.Users;
import ru.akaleganov.service.Dispatch;
import ru.akaleganov.service.ServiceAddObjects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SigninServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/vievs/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = Dispatch.getInstance()
                .access("findByLoginPass", ServiceAddObjects.getInstance()
                        .addUser(String.format("{\"login\":\"%s\", \"password\":\"%s\"}", req.getParameter("login"), req.getParameter("pswd"))));
        if (user.getLogin() == null) {
            req.setAttribute("err", "Пользователь или пароль указан не верно");
            req.getRequestDispatcher("/WEB-INF/vievs/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("userID", user.getId());
            req.getSession().setAttribute("login", user.getLogin());
            req.getSession().setAttribute("role", user.getRoles().getRole());
            req.getSession().setAttribute("phList", new ArrayList<Photo>());
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        }
    }
}
