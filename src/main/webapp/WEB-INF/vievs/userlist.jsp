<%--
  Created by IntelliJ IDEA.
  User: USERCRB001
  Date: 24.05.2019
  Time: 9:16
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
    </script>
    <script charset="UTF-8" type="text/javascript">
        <%@include file="/WEB-INF/vievs/js/sortedTable.js" %>
    </script>
    <script charset="UTF-8" type="text/javascript"><%@include file="/WEB-INF/vievs/js/userlistJS.js"%> </script>
    <title>userlist</title>
    <script>
        <c:set var="log" value="${login}"/>
        $(document).ready(function () {
            <c:if test="${role eq 'ADIN'}">
            listuser();
            </c:if>
            <c:if test="${!(role eq 'ADIN')}">
            console.log(${log});
            <%--user(<c:out value="${login}"/>);--%>
            </c:if>
        })
    </script>
</head>
<body>
<div class="container">
    <p>TodoList:</p>
    <table class="table table-striped" id="todolist_table">
        <thead class="thead-dark">
        <tr>
            <th onclick="sortTable(0, 'todolist_table')">ID &darr;</th>
            <th onclick="sortTable(1, 'todolist_table')">Name &darr;<span class="fi-sort-ascending"></span></th>
            <th onclick="sortTable(2, 'todolist_table')">Login &darr;</th>
            <th onclick="sortTable(3, 'todolist_table')">Role &darr;</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
</body>
</html>
