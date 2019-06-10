<%--
  Created by IntelliJ IDEA.
  User: Lis
  Date: 10 июн 19
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>TODO_List</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <script src="js/jsforindex.js"></script>
    <script src="js/sortedteble.js"></script>
</head>
<br/>
<body>
<div class="container">

    <div class="containerbacket">
        <p>Backet:</p>
        <table class="table table-striped" id="backet_table">
            <thead class="thead-dark">
<c:forEach items="${list}" var="u">
            <tr>
                <th onclick="sortTable(1, 'todolist_table')">Название &darr;<span class="fi-sort-ascending"></span></th>
                <th onclick="sortTable(2, 'todolist_table')">Количесвто &darr;</th>
                <th>Удалить из корзины</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
    <div class="container">
        <p>TodoList:</p>
        <table class="table table-striped" id="todolist_table">
            <thead class="thead-dark">
            <tr>
                <th onclick="sortTable(0, 'todolist_table')">ID &darr;</th>
                <th onclick="sortTable(1, 'todolist_table')">Название &darr;<span class="fi-sort-ascending"></span></th>
                <th onclick="sortTable(2, 'todolist_table')">Цена &darr;</th>
                <th>Добавить в корзину</th>
                <th>Удалить из корзины</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
</body>
</html>