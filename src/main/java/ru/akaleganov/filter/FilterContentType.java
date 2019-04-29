package ru.akaleganov.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterContentType implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        if (req.getRequestURI().contains("/todolist")) {
            res.setContentType("text/json; charset=windows-1251");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
