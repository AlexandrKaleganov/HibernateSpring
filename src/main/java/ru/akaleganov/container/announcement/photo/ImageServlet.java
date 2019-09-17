package ru.akaleganov.container.announcement.photo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.akaleganov.modelsannot.Announcement;
import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.service.AnnouncementDispatch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * сервлет отрисовывает фотографии по id и удалаяет фотографии
 */
@Controller
public class ImageServlet {
    private static final Logger LOGGER = Logger.getLogger(ImageServlet.class);

    @GetMapping(value = "/image")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        Photo photo = AnnouncementDispatch.getInstance().access("findByIdPhoto", new Photo(Integer.valueOf(req.getParameter("id"))));
        resp.setContentType("image/jpeg");
        resp.setContentLength(photo.getPhoto().length);
        try {
            resp.getOutputStream().write(photo.getPhoto());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @PostMapping(value = "/image")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        if (req.getParameter("action").contains("delete")) {
            System.out.println(req.getParameter("idPhoto"));
            AnnouncementDispatch.getInstance().access("deletePhotoById", new Photo(Integer.parseInt(req.getParameter("idPhoto"))));
        }
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(AnnouncementDispatch.getInstance()
                    .access("findByIdAn", new Announcement(Integer.valueOf(req.getParameter("idAn"))))));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
