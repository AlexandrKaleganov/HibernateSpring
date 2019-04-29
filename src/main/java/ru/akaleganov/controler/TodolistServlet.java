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

public class TodolistServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(TodolistServlet.class);
    Dispatch dispatch = Dispatch.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(this.dispatch.access(req.getParameter("action"), new Item(), new ArrayList<Item>())));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(
                    new ObjectMapper().writeValueAsString(
                            this.dispatch.access(req.getParameter("action"), new ServiceItem().addItem(req.getParameter("descr"), req.getParameter("done")), new Item())));
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
