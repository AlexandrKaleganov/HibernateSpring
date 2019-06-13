package ru.akaleganov.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.akaleganov.models.Item;
import ru.akaleganov.service.Dispatch;
import ru.akaleganov.service.ServiceItem;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Сервлет для работы с хешмапой из сессии для загрузки данных в корзину
 */
public class BasketLoadServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(BasketLoadServlet.class);

    /**
     * метод будет возвращать хешмапу из сесии
     *
     * @param req  всегда приходит запрос на получение хешмапы
     * @param resp ответ в виде json хешмапа из сессии
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(req.getSession().getAttribute("basketMap")));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Dispatch.getInstance().access("findById", new Item(Long.valueOf(req.getParameter("id"))))
     * метод находит по id нужный айтем, в  ServiceItem.getInstance()
     * у нас находятся методы для работы с хешмапой , сервис принимает мапу,
     * и item  и в зависимости от action производит определённые дейтвия
     *
     * @param req  запрос содержит команду, которая определяет что будет происходить с хешмапой в сессии и бует вызываться продукт по id из бд
     * @param resp дальше просто вызывается get  для получения карты из сесии
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //noinspection unchecked
        ServiceItem.getInstance().access(req.getParameter("action"),
                Dispatch.getInstance().access("findById", new Item(Long.valueOf(req.getParameter("id")))),
                (HashMap<Item, Integer>) req.getSession().getAttribute("basketMap"));
        this.doGet(req, resp);
    }

}
