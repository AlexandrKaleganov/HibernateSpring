package ru.akaleganov.container;

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
import ru.akaleganov.service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ServletIndex {
    private static final Logger LOGGER = Logger.getLogger(ServletIndex.class);
    private final AnnouncementService announcementService;
    private final UsersService usersService;

    @Autowired
    public ServletIndex(AnnouncementService announcementService, UsersService usersService) {
        this.announcementService = announcementService;
        this.usersService = usersService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @GetMapping(value = "/findById/{id}")
    public String findById(Principal principal, ModelMap map, @PathVariable Long id, HttpServletRequest req) {
        LOGGER.debug(principal.getName());
        Announcement announcement = this.announcementService.findByID(new Announcement(id));
        LOGGER.info(announcement);
        if (announcement.getId() == 0) {
            announcement.setAuthor(this.usersService.findByLogin(
                    new Users(principal.getName())));
        }
        map.addAttribute("an", announcement);
        return "announ/edit";
    }

    @PostMapping(value = "/edit")
    public ResponseEntity<Announcement> updateOrReplace(ModelMap modelMap, HttpServletRequest req, HttpServletResponse resp) {
        if (req.getSession().getAttribute("phList") == null) {
            req.getSession().setAttribute("phList", new ArrayList<Photo>());
        }
        Announcement announcement = this.announcementService.edit(ServiceAddObjects.getInstance().addAllObject(
                req.getParameter("an"), req.getParameter("car"), (ArrayList<Photo>) req.getSession().getAttribute("phList")));
        return ResponseEntity.ok(this.announcementService.findByID(announcement));
    }

    @PostMapping(value = "/delete")
    public void delete(@RequestParam("idAn") Long idAn, @RequestParam("idCar") Long idCar, HttpServletResponse resp, HttpServletRequest req) throws IOException, ServletException {

        this.announcementService.delete(ServiceAddObjects.getInstance().addAllObject(
                idAn, idCar));
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
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
