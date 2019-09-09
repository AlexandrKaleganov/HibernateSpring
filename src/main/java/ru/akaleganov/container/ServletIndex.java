package ru.akaleganov.container;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.akaleganov.modelsannot.Announcement;
import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.modelsannot.Users;
import ru.akaleganov.service.Dispatch;
import ru.akaleganov.service.ServiceAddObjects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ServletIndex extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ServletIndex.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/vievs/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String action = req.getParameter("action");
        System.out.println(req.getParameter("an"));
        if (action.contains("findByIdAn")) {
            try {
                Announcement announcement = Dispatch.getInstance().access(action, new Announcement(Integer.valueOf(req.getParameter("an"))));
                if (announcement.getId() == 0) {
                    announcement.setAuthor(new Users((Integer) req.getSession().getAttribute("userID")));
                    announcement.getAuthor().setLogin((String) req.getSession().getAttribute("login"));
                }
                req.setAttribute("an", announcement);
                req.getRequestDispatcher("WEB-INF/vievs/announ/edit.jsp").forward(req, resp);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else if (action.contains("deleteAn")) {
            Dispatch.getInstance().access("deleteAn", ServiceAddObjects.getInstance().addAllObject(
                    req.getParameter("an"), req.getParameter("car"),
                    new ArrayList<>()));
            try {
                this.doGet(req, resp);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }

        } else if ("toShowForTheLastDay".equals(action)) {
            LOGGER.debug("сработала логика = " + action);
            try {
                PrintWriter writer = new PrintWriter(resp.getOutputStream());
                writer.append(new ObjectMapper().writeValueAsString(Dispatch.getInstance().access(action,
                        ServiceAddObjects.getInstance().addAllObject(
                                req.getParameter("an"), req.getParameter("car"),
                                (ArrayList<Photo>) req.getSession().getAttribute("phList")))));
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else if ("filter".equals(action)) {
            LOGGER.debug("сработала логика = " + action);
            try {
                PrintWriter writer = new PrintWriter(resp.getOutputStream());
                writer.append(new ObjectMapper().writeValueAsString(Dispatch.getInstance().access(req.getParameter("param"),
                        ServiceAddObjects.getInstance().addMarka(req.getParameter("marka")))));
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            try {
                PrintWriter writer = new PrintWriter(resp.getOutputStream());
                writer.append(new ObjectMapper().writeValueAsString(Dispatch.getInstance().access(action,
                        ServiceAddObjects.getInstance().addAllObject(
                                req.getParameter("an"), req.getParameter("car"),
                                (ArrayList<Photo>) req.getSession().getAttribute("phList")))));
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
