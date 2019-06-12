package ru.akaleganov.container.users;

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

public class ServletUserList extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ServletUserList.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/vievs/users/userlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String action = req.getParameter("action");
        if (action.contains("findByIdUser")) {
            try {
                req.setAttribute("user", Dispatch.getInstance().access(action, new Users(Integer.valueOf(req.getParameter("us")))));
                req.getRequestDispatcher("WEB-INF/vievs/users/edit.jsp").forward(req, resp);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else if (action.contains("deleteUser")) {
            req.setAttribute("user", Dispatch.getInstance().access("deleteUser", new Users(Integer.valueOf(req.getParameter("us")))));
            try {
                req.getRequestDispatcher("WEB-INF/vievs/users/userlist.jsp").forward(req, resp);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            try {
                PrintWriter writer = new PrintWriter(resp.getOutputStream());
                writer.append(new ObjectMapper().writeValueAsString(Dispatch.getInstance().access(action,
                        ServiceAddObjects.getInstance().addUser(req.getParameter("us")))));
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
