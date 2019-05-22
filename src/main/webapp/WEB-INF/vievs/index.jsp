<%--
  Created by IntelliJ IDEA.
  User: Lis
  Date: 23 мая 19
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="js/out.js"/>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <!-- Brand/logo -->
    <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/">Logo</a>
    <!-- Links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.servletContext.contextPath}/create">Добавить пользователя</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.servletContext.contextPath}/list">Список пользователей</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.servletContext.contextPath}/404">Подать объявление</a>
        </li>
    </ul>
    <button type="submit" style="display: block; margin-left: auto;" class="btn btn-outline-danger" onclick="exit()">ВЫХОД</button>
</nav>
</body>
</html>
