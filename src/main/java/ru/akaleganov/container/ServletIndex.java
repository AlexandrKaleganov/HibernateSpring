package ru.akaleganov.container;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.akaleganov.modelsannot.Users;
import ru.akaleganov.service.Dispatch;
import ru.akaleganov.service.ServiceAddObjects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class ServletIndex extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ServletIndex.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/vievs/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.contains("findbyidan")) {
            try {
                req.setAttribute("an", Dispatch.getInstance().access(action, Optional.of(new Users(Integer.valueOf(req.getParameter("an"))))));
                req.getRequestDispatcher("WEB-INF/vievs/announ/edit.jsp").forward(req, resp);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else if (action.contains("deletean")) {
            req.setAttribute("an", Dispatch.getInstance().access("deletean", Optional.of(new Users(Integer.valueOf(req.getParameter("an"))))));
            try {
                req.getRequestDispatcher("WEB-INF/vievs/users/userlist.jsp").forward(req, resp);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            try {
                PrintWriter writer = new PrintWriter(resp.getOutputStream());
                writer.append(new ObjectMapper().writeValueAsString(Dispatch.getInstance().access(action,
                        Optional.of(ServiceAddObjects.getInstance().addAnnouncement(req.getParameter("an"))))));
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
