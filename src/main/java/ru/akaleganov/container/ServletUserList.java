package ru.akaleganov.container;

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
import java.io.Writer;
import java.util.ArrayList;
import java.util.Optional;

public class ServletUserList extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(ServletUserList.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/vievs/userlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        PrintWriter writer = null;
        System.out.println(req.getParameter("action"));
        System.out.println(req.getParameter("us"+ "как нулл как нуулл то????"));
        ArrayList<Users> userlist =Dispatch.getInstance().access(req.getParameter("action"),
                Optional.of(ServiceAddObjects.getInstance().addUser(req.getParameter("us"))));
        System.out.println(userlist.get(0));
        try {
            writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(userlist));
            System.out.println(writer.toString());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
