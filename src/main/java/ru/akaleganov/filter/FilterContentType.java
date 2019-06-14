package ru.akaleganov.filter;

import org.apache.log4j.Logger;
import ru.akaleganov.models.Item;
import ru.akaleganov.service.SFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Alexander Kaleganov
 * @since 13/06/2019
 * фильтр для всех сервлетов
 */
public class FilterContentType implements Filter {
    private static final Logger LOGGER = Logger.getLogger(FilterContentType.class);

    /**
     * устанавливает res.setContentType("text/json; charset=windows-1251"), для респонсов
     * если  ссылка содержит /todolist или /backet также проверяет, есть ли в текучщей сессии HeshMap
     * она отсутствует то создастся новая мапа
     *
     * @param servletRequest  фильтр для сервлетов перехватывает все запросы
     * @param servletResponse фильтр для сервлетов перехватывает все запросы
     * @param filterChain     {@link FilterChain}
     * @throws IOException      {@link IOException} исключение может выбросить метод  filterChain.doFilter()
     * @throws ServletException {@link ServletException} исключение может выбросить метод  filterChain.doFilter()
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        if (req.getSession().getAttribute("basketMap") == null) {
            req.getSession().setAttribute("basketMap", new HashMap<Item, Integer>());
        }
        if (req.getRequestURI().contains("/todolist") || req.getRequestURI().contains("/basket")) {
            res.setContentType("text/json; charset=windows-1251");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * закрываем фактори, оно должно быть инициализированно 1 раз для всего приложения
     * и закрыто 1 раз
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
