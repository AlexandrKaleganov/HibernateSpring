package ru.akaleganov.filter;

import org.apache.log4j.Logger;
import ru.akaleganov.modelsannot.Photo;
import ru.akaleganov.service.ServiceAddObjects;
import ru.akaleganov.service.Sfactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthFilter implements Filter {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;    //два запроса переделываем под HttpServlet
        HttpServletResponse response = (HttpServletResponse) res;
        if (request.getRequestURI().contains("/signin")) {     //если лезим на страницу авторизации - то
            chain.doFilter(req, res);                           //фильтр нас пропускает и наши запросы де нас в свою очередь перекинет на loginIN.jsp'
        } else {
            if (request.getSession().getAttribute("login") == null) {   //если в сессии нет  атрибута login
                response.sendRedirect(String.format("%s/signin", request.getContextPath())); //то нас опять бросит на сервлет signin где перекинет на loginIN.jsp
                return;                                                                  //и дальше метод завершится
            }
            if (((HttpServletRequest) req).getRequestURI().contains("/upload")) {
                chain.doFilter(req, res);
                return;
            }
            if (((HttpServletRequest) req).getRequestURI().contains("/upload")) {
                chain.doFilter(req, res);
                return;
            }
            if (req.getParameter("exit") != null) {
                request.getSession().invalidate();
                response.sendRedirect(String.format("%s/signin", request.getContextPath())); //то нас опять бросит на сервлет signin где перекинет на loginIN.jsp
                return;
            }
            req.setCharacterEncoding("UTF-8");
            res.setContentType("text/json; charset=windows-1251");
            chain.doFilter(req, res);   //а вот если всё пучком и запрос не на страницу авторизации и сессия содержит логин то фильтр нас пропускает куда угодно
        }
    }

    @Override
    public void destroy() {
        try {
            Sfactory.getINSTANCE().close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
