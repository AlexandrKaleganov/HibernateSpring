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
import java.util.ArrayList;
import java.util.HashMap;

public class PriceServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(PriceServlet.class);
    Dispatch dispatch = Dispatch.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(this.dispatch.access(req.getParameter("action"), new Item())));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
//        Item item = Dispatch.getInstance().access(req.getParameter("action"), new Item(Long.valueOf(req.getParameter("id"))));
//        HashMap<String, Integer> map = (HashMap<String, Integer>) req.getSession().getAttribute("backet");
//        map.put(item.getName(), map.get(item.getName()) + 1);
//        try {
//            PrintWriter writer = new PrintWriter(resp.getOutputStream());
//            writer.append(
//                    new ObjectMapper().writeValueAsString(map));
//            writer.flush();
//        } catch (IOException e) {
//            LOGGER.error(e.getMessage(), e);
//        }
    }
}
