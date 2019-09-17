package ru.akaleganov.container;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.modelsannot.Users;
import ru.akaleganov.service.ServiceAddObjects;
import ru.akaleganov.service.UserDispatch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
@Controller
@RequestMapping("/api")
public class SigninServlet extends HttpServlet {
    @RequestMapping(value = "signin", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @RequestMapping(value = "signin", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = UserDispatch.getInstance()
                .access("findByLoginPass", ServiceAddObjects.getInstance()
                        .addUser(String.format("{\"login\":\"%s\", \"password\":\"%s\"}", req.getParameter("login"), req.getParameter("pswd"))));
        if (user.getLogin() == null) {
            req.setAttribute("err", "Пользователь или пароль указан не верно");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("userID", user.getId());
            req.getSession().setAttribute("login", user.getLogin());
            req.getSession().setAttribute("role", user.getRoles().getRole());
            req.getSession().setAttribute("phList", new ArrayList<Photo>());
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        }
    }
}
