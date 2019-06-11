package ru.akaleganov.filter;

import org.apache.log4j.Logger;
import ru.akaleganov.models.Item;
import ru.akaleganov.service.Dispatch;
import ru.akaleganov.service.SFactory;
import ru.akaleganov.service.ServiceItem;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * фильтр для всех сервлетов
 *
 */
public class FilterContentType implements Filter {
    private static final Logger LOGGER = Logger.getLogger(FilterContentType.class);

    /**
     *  устанавливает res.setContentType("text/json; charset=windows-1251"), для респонсов
     *  если  ссылка содержит /todolist или /backet также проверяет, есть ли в текучщей сессии HeshMap
     *  она отсутствует то создастся новая мапа
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        if (req.getParameter("clear") != null) {
            req.getSession().invalidate();
        }
        if (req.getSession().getAttribute("backetmap") == null) {
            ArrayList<Item> list = Dispatch.getInstance().access("list", new Item());
            req.getSession().setAttribute("backetmap", new HashMap<Item, Integer>());
        }
        if (req.getRequestURI().contains("/todolist") || req.getRequestURI().contains("/backet")) {
            res.setContentType("text/json; charset=windows-1251");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * закрываем фактори, оно должно быть инициализированно 1 раз для всего приложения
     */
    @Override
    public void destroy() {
        try {
            SFactory.getSfactory().close();
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }
    }
}
