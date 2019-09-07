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
        <%@include file="/WEB-INF/vievs/js/filterTable.js"%>
    </script>
    <title>index</title>
</head>
<body id="body">
</body>
<div id="navipanel">
</div>
<div class="username" id="username" style="float: right;">
</div>
<div class="container">
    <p>Список объявлений: </p>
    <form action="${pageContext.servletContext.contextPath}/" method="post">
        <input type="hidden" name="an" value="0">
        <input type="hidden" name="action" value="findByIdAn">
        <input type="submit" value="Подать объявление">
    </form>
    <br/>
    <div>
        <select id="filter" onchange="filterAction()">
            <option></option>
            <option value="toShowForTheLastDay">- показать за последний день</option>
            <option value="toShowWithAPhoto">- показать с фото</option>
            <option value="toShowACertainBrand">- показать определенной марки</option>
        </select>
        <select hidden="true" id="marka" onchange="filterAction()">
            <option value="0"></option>
        </select>
    </div>
    <br/>
    <br/>
    <br/>
    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <table class="table table-striped" id="todolist_table">
        <thead class="thead-dark">
        <tr>
            <th onclick="sortTable(0, 'todolist_table')">ID &darr;</th>
            <th onclick="sortTable(1, 'todolist_table')">Название объявления &darr;<span
                    class="fi-sort-ascending"></span></th>
            <th onclick="sortTable(2, 'todolist_table')">Дата создания &darr;</th>
            <th onclick="sortTable(3, 'todolist_table')">Марка &darr;</th>
            <th onclick="sortTable(4, 'todolist_table')">Модель &darr;</th>
            <th onclick="sortTable(5, 'todolist_table')">Год выпуска &darr;</th>
            <th onclick="sortTable(6, 'todolist_table')">Пользователь &darr;</th>
            <th onclick="sortTable(7, 'todolist_table')">Продано &darr;</th>
            <th>Просмотр</th>
        </tr>
        </thead>
        <tbody id="myTable"></tbody>
    </table>
</div>
</html>
