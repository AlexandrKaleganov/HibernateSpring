package ru.akaleganov.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.akaleganov.models.Item;
import ru.akaleganov.service.Dispatch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class BacketLoadServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(BacketLoadServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(req.getSession().getAttribute("backet")));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/json; charset=utf-8");
        String action = req.getParameter("action");
        Item item = Dispatch.getInstance().access("findbyid", new Item(Long.valueOf(req.getParameter("id"))));
//        HashMap<Item, Integer> map = (HashMap<Item, Integer>) session.getAttribute("backet");
//        map.forEach((k, v) -> {
//            System.out.println(k.toString() + v);
//        });
//        System.out.println(map.get(item));
//        if (action.contains("add")) {
//            map.put(item, map.get(item.getName()) + 1);
//        } else {
//            map.put(item, map.get(item.getName()) - 1);
//        }
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(
                    new ObjectMapper().writeValueAsString(item));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    //    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
//
//    }
}
