package ru.akaleganov.container;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.akaleganov.service.Dispatch;
import ru.akaleganov.service.ServiceAddObjects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * пока только получает список ролей, можно добавить добавление редактирование и удаление ролей
 */
public class ServletRoles extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ServletUserList.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //пока никакой реализации это метод свободен
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String action = req.getParameter("action");
            try {
                PrintWriter writer = new PrintWriter(resp.getOutputStream());
                writer.append(new ObjectMapper().writeValueAsString(Dispatch.getInstance().access(action,
                        Optional.of(ServiceAddObjects.getInstance().addRole(req.getParameter("ro"))))));
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
        }
    }
}
