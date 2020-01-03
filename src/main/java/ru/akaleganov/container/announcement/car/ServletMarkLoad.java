package ru.akaleganov.container.announcement.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.akaleganov.service.MarkService;
import ru.akaleganov.service.ModelService;
import ru.akaleganov.service.ServiceAddObjects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Controller
public class ServletMarkLoad {
    private static final Logger LOGGER = Logger.getLogger(ServletMarkLoad.class);
private final MarkService markService;
private final ModelService modelService;
    public ServletMarkLoad(MarkService markService, ModelService modelService) {
        this.markService = markService;
        this.modelService = modelService;
    }

    @PostMapping(value = "/markLoad")
    protected void markLoad(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(this.markService.findAll()));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * найдёт список моделей по марке авто
     * @param req {содержит запрос марки id}
     * @param resp {ответ}
     */
    @PostMapping(value = "/modelLoad")
    protected void modelLoad(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(this.modelService.findByMarkId(
                    ServiceAddObjects.getInstance().addMark(req.getParameter("m"))
            )));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
