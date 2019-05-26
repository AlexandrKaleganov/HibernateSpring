package ru.akaleganov.container;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.akaleganov.modelsannot.Users;
import ru.akaleganov.service.Dispatch;
import ru.akaleganov.service.ServiceAddObjects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class ServletUserList extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(ServletUserList.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/vievs/userlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        System.out.println(req.getParameter("us"));
        try {
            System.out.println(new ObjectMapper().writeValueAsString(Dispatch.getInstance().access(req.getParameter("action"),
                    Optional.of(ServiceAddObjects.getInstance().addUser(req.getParameter("us"))))));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        try {
            PrintWriter  writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(Dispatch.getInstance().access(req.getParameter("action"),
                    Optional.of(ServiceAddObjects.getInstance().addUser(req.getParameter("us"))))));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
