package ru.akaleganov.container.announcement.photo;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.akaleganov.domain.Announcement;
import ru.akaleganov.domain.Photo;
import ru.akaleganov.service.AnnouncementService;
import ru.akaleganov.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * сервлет отрисовывает фотографии по id и удалаяет фотографии
 */
@Controller
public class ImageServlet {
    private static final Logger LOGGER = Logger.getLogger(ImageServlet.class);
    private final AnnouncementService announcementService;
    private final PhotoService photoService;

    public ImageServlet(AnnouncementService announcementService, PhotoService photoService) {
        this.announcementService = announcementService;
        this.photoService = photoService;
    }

    @GetMapping(value = "/image")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("сработал метод получение фотографии");
        Photo photo = this.photoService.findByID(new Photo(Long.parseLong(req.getParameter("id"))));
        resp.setContentType("image/jpeg");
        resp.setContentLength(photo.getPhoto().length);
        try {
            resp.getOutputStream().write(photo.getPhoto());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @PostMapping(value = "/image")
    protected ResponseEntity<Announcement> doPost(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("action").contains("delete")) {
            System.out.println(req.getParameter("idPhoto"));
            this.photoService.delete(new Photo(Integer.parseInt(req.getParameter("idPhoto"))));
        }
        Announcement announcement = this.announcementService.findByID(
                new Announcement(Integer.parseInt(req.getParameter("idAn"))));
        LOGGER.debug(announcement.getCar().getPhoto().size());
        return ResponseEntity.ok(announcement);
    }
}
