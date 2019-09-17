package ru.akaleganov.container.announcement.photo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.service.ServiceAddObjects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
public class ServletSession {
    private final static Logger LOGGER = Logger.getLogger(ServletSession.class);

    @GetMapping(value = "/sessionPhotos")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @PostMapping(value = "/sessionPhotos")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json; charset=windows-1251");
        LOGGER.info("пришли фото в сессию");
        if (req.getParameter("action").contains("delete")) {
            ServiceAddObjects.getInstance().deleteOnePhoto((ArrayList<Photo>) req.getSession().getAttribute("phList"),
                    Integer.parseInt(req.getParameter("index")));
            System.out.println(((ArrayList<Photo>) req.getSession().getAttribute("phList")).size());
        }
        if (req.getParameter("action").contains("clearPhList")) {
            ServiceAddObjects.getInstance().clearListSession(
                    (ArrayList<Photo>) req.getSession().getAttribute("phList"));
            System.out.println("СРАБОТАЛО");
        }
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(req.getSession().getAttribute("phList")));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
