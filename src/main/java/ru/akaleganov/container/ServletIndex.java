package ru.akaleganov.container;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.akaleganov.domain.Announcement;
import ru.akaleganov.domain.Mark;
import ru.akaleganov.domain.Photo;
import ru.akaleganov.domain.Users;
import ru.akaleganov.service.AnnouncementService;
import ru.akaleganov.service.ServiceAddObjects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ServletIndex {
    private static final Logger LOGGER = Logger.getLogger(ServletIndex.class);
    private final AnnouncementService announcementService;

    @Autowired
    public ServletIndex(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @GetMapping(value = "/findById/{id}")
    public String findById(ModelMap map, @PathVariable int id, HttpServletRequest req) {
        Announcement announcement = this.announcementService.findByID(new Announcement(id));
        LOGGER.info(announcement);
        if (announcement.getId() == 0) {
            announcement.setAuthor(new Users((Long) req.getSession().getAttribute("userID")));
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
            writer.append(new ObjectMapper().writeValueAsString(this.announcementService.edit(ServiceAddObjects.getInstance().addAllObject(
                    req.getParameter("an"), req.getParameter("car"),
                    (ArrayList<Photo>) req.getSession().getAttribute("phList")))));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/delete")
    public String delete(HttpServletRequest req, HttpServletResponse resp) {
        this.announcementService.delete(ServiceAddObjects.getInstance().addAllObject(
                req.getParameter("an"), req.getParameter("car"),
                new ArrayList<>()));
        return "redirect:";
    }

    /**
     * @throws ServletException
     */
    @GetMapping(value = "/listAn")
    protected ResponseEntity<List<Announcement>> findAll() throws ServletException {
        return ResponseEntity.ok().body(this.announcementService.findAll());
    }


    /**
     * @param mark  id марки не обяательный параметр
     * @param param параметр указывает какой фильтр используется
     * @return вернёт результат
     */
    @GetMapping(value = "/filterAn")
    protected ResponseEntity<List<Announcement>> findAllFilter(@RequestParam(value = "mark", required = false)
                                                                       Long mark,
                                                               @RequestParam("param") String param) {
        if (param.equals("toShowACertainBrand")) {
            LOGGER.info("toShowACertainBrand");
            return ResponseEntity.ok().body(this.announcementService.toShowACertainBrand(new Mark(mark)));
        } else if (param.equals("toShowForTheLastDay")) {
            LOGGER.info("toShowForTheLastDay");
            return ResponseEntity.ok().body(this.announcementService.toShowForTheLastDay());
        } else {
            LOGGER.info("toShowWithAPhoto");
            return ResponseEntity.ok().body(this.announcementService.toShowWithAPhoto());
        }


    }
}
