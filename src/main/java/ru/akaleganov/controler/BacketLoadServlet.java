package ru.akaleganov.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.akaleganov.models.Item;
import ru.akaleganov.service.Dispatch;
import ru.akaleganov.service.ServiceItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class BacketLoadServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(BacketLoadServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            HashMap<Item, Integer> map = (HashMap<Item, Integer>) req.getSession().getAttribute("backetmap");
            writer.append(new ObjectMapper().writeValueAsString(map));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HashMap<Item, Integer> map = (HashMap<Item, Integer>) req.getSession().getAttribute("backetmap");
        Item item = Dispatch.getInstance().access("findbyid", new Item(Long.valueOf(req.getParameter("id"))));
        ServiceItem.getInstance().access(action, item, map);
        System.out.println(map.get(item));
        this.doGet(req, resp);
    }

}