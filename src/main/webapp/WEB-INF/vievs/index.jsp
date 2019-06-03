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

    <script charset="UTF-8" type="text/javascript">
        <%@include file="/WEB-INF/vievs/js/navibar.js" %>
        <%@include file="/WEB-INF/vievs/js/sortedTable.js" %>
        <%@include file="/WEB-INF/vievs/js/index.js"%>
    </script>
</head>
<body id="body">
</body>
<div id="navipanel">
</div>
<div class="username" id="username" style="float: right;">
</div>
<div class="container">
    <p>Список объявлений:
    <form action="${pageContext.servletContext.contextPath}/createannoun" method="post">
        <input type="hidden" name="us" value="0">
        <input type="hidden" name="action" value="findbyiduser">
        <input type="submit" value="Подать объявление">
    </form>
    </p>
    <table class="table table-striped" id="todolist_table">
        <thead class="thead-dark">
        <tr>
            <th onclick="sortTable(0, 'todolist_table')">ID &darr;</th>
            <th onclick="sortTable(1, 'todolist_table')">Название объявления &darr;<span class="fi-sort-ascending"></span></th>
            <th onclick="sortTable(2, 'todolist_table')">Дата создания &darr;</th>
            <th onclick="sortTable(3, 'todolist_table')">Пользователь &darr;</th>
            <th onclick="sortTable(4, 'todolist_table')">Продано &darr;</th>
            <th>Просмотр</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
</html>
