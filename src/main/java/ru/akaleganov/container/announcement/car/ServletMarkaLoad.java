package ru.akaleganov.container.announcement.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.akaleganov.service.AnnouncementDispatch;
import ru.akaleganov.service.ServiceAddObjects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Controller
public class ServletMarkaLoad {
    private static final Logger LOGGER = Logger.getLogger(ServletMarkaLoad.class);

    @PostMapping(value = "/markaLoad")
    protected void markaLoad(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(AnnouncementDispatch.getInstance().access(req.getParameter("action"),
                    ServiceAddObjects.getInstance().addMarka(req.getParameter("m")))));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
