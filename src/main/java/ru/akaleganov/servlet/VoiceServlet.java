package ru.akaleganov.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.akaleganov.model.Task;
import ru.akaleganov.service.CalculatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class VoiceServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(VoiceServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     *
     * @param req получаем объект {@link Task}
     * @param resp выводим объект {@link Task} с посчитанным полем result
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/json; charset=windows-1251");
        PrintWriter writer = null;
        try {
            Task task = new ObjectMapper().readValue(req.getParameter("task"), Task.class);
            CalculatorService.getCalculatorService().count(task);
            writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(task));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        assert writer != null;
        writer.flush();
    }
}
