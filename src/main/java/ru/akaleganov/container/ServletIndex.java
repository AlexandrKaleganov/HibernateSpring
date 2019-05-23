package ru.akaleganov.container;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletIndex extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("role", req.getSession().getAttribute("role"));
//        req.setAttribute("login", req.getSession().getAttribute("login"));
        req.getRequestDispatcher("/WEB-INF/vievs/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String r = "<nav class=\"navbar navbar-expand-sm bg-dark navbar-dark\">\n" +
                "    <!-- Brand/logo -->\n" +
                "    <a class=\"navbar-brand\" href=\"${pageContext.servletContext.contextPath}/\">Logo</a>\n" +
                "    <!-- Links -->\n" +
                "    <ul class=\"navbar-nav\">\n" +
                "        <li class=\"nav-item\">\n" +
                "            <a class=\"nav-link\" href=\"${pageContext.servletContext.contextPath}/create\">Добавить пользователя</a>\n" +
                "        </li>\n" +
                "        <li class=\"nav-item\">\n" +
                "            <a class=\"nav-link\" href=\"${pageContext.servletContext.contextPath}/list\">Список пользователей</a>\n" +
                "        </li>\n" +
                "        <li class=\"nav-item\">\n" +
                "            <a class=\"nav-link\" href=\"${pageContext.servletContext.contextPath}/404\">Подать объявление</a>\n" +
                "        </li>\n" +
                "    </ul>\n" +
                "    <button type=\"submit\" style=\"display: block; margin-left: auto;\" class=\"btn btn-outline-danger\" onclick=\"exit()\">ВЫХОД</button>\n" +
                "</nav>";
    }
}
