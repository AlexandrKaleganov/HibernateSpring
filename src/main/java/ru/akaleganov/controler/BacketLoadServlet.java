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

    /**
     * метод будет возвращать хешмапу из сесии
     * @param req
     * @param resp
     * @throws ServletException
     */
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

    /**
     * ispatch.getInstance().access("findbyid", new Item(Long.valueOf(req.getParameter("id"))))
     * метод находит по id нужный айтем, в  ServiceItem.getInstance()
     * у нас находятся методы для работы с хешмапой , сервис принимает мапу,
     * и item  и в зависимости от action производит определённые дейтвия
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HashMap<Item, Integer> map = (HashMap<Item, Integer>) req.getSession().getAttribute("backetmap");
        Item item = Dispatch.getInstance().access("findbyid", new Item(Long.valueOf(req.getParameter("id"))));
        ServiceItem.getInstance().access(action, item, map);
        this.doGet(req, resp);
    }

}
