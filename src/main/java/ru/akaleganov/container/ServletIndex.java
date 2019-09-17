package ru.akaleganov.container;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.akaleganov.modelsannot.Announcement;
import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.modelsannot.Users;
import ru.akaleganov.service.AnnouncementDispatch;
import ru.akaleganov.service.ServiceAddObjects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
public class ServletIndex extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ServletIndex.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @GetMapping(value = "/findById/{id}")
    public String findById(ModelMap map, @PathVariable int id, HttpServletRequest req) {
        Announcement announcement = AnnouncementDispatch.getInstance().access("findByIdAn", new Announcement(id));
        LOGGER.info(announcement);
        if (announcement.getId() == 0) {
            announcement.setAuthor(new Users((Integer) req.getSession().getAttribute("userID")));
            announcement.getAuthor().setLogin((String) req.getSession().getAttribute("login"));
        }
        map.addAttribute("an", announcement);
        return "announ/edit";
    }

    @PostMapping(value = "/edit")
    public void updateOrReplace(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");

        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(AnnouncementDispatch.getInstance().access(action,
                    ServiceAddObjects.getInstance().addAllObject(
                            req.getParameter("an"), req.getParameter("car"),
                            (ArrayList<Photo>) req.getSession().getAttribute("phList")))));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/delete")
    public String delete(HttpServletRequest req, HttpServletResponse resp) {
        AnnouncementDispatch.getInstance().access("deleteAn", ServiceAddObjects.getInstance().addAllObject(
                req.getParameter("an"), req.getParameter("car"),
                new ArrayList<>()));
        return "redirect:";
    }

    @PostMapping(value = "/listAn")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String action = req.getParameter("action");
        System.out.println(req.getParameter("an"));
        if ("filter".equals(action)) {
            LOGGER.debug("сработала логика = " + action);
            try {
                PrintWriter writer = new PrintWriter(resp.getOutputStream());
                writer.append(new ObjectMapper().writeValueAsString(AnnouncementDispatch.getInstance().access(req.getParameter("param"),
                        ServiceAddObjects.getInstance().addMarka(req.getParameter("marka")))));
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            LOGGER.debug("сработала логика = " + action);
            try {
                PrintWriter writer = new PrintWriter(resp.getOutputStream());
                writer.append(new ObjectMapper().writeValueAsString(AnnouncementDispatch.getInstance().access(action,
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
