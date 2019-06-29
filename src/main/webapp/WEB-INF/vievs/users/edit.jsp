<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Lis
  Date: 29 мая 19
  Time: 0:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
        <%@include file="/WEB-INF/vievs/js/users/editJS.js" %>
    </script>

    <title>Edit User</title>
</head>
<body>
<div id="navipanel">
</div>
<div class="username" id="username" style="float: right;">
</div>
<br/>
<br id="result"/>
<br/>
<div>
    <form class="form-inline">
        <div class="form-group">
            <label for="id"></label>
            <input type="hidden" class="form-control" name="id" value="${user.id}" title="Enter ID." id="id">
        </div><c:out value="${user.name}"/>
        <div class="form-group">
            <label for="name">Имя:</label>
            <input type="text" class="form-control" name="name" value="${user.name}" title="Enter name." id="name">
        </div>
        <div class="form-group">
            <label for="login">Маил:</label>
            <input type="text" class="form-control" name="mail" value="${user.login}" title="Enter login." id="login">
        </div>
        <div class="form-group">
            <label for="password">Проль:</label>
            <input type="text" class="form-control" name="password" value="${user.password}" title="Enter pass."
                   id="password">
        </div>
        <div class="form-group">
            <label for="roles">Роли:</label>
            <select class="form-control" name="roles" title="Enter the attribute access" id="roles">
                <option value="${user.roles.id}">${user.roles.role}</option>
            </select>
        </div>
        <button type="button" id="action" name="action" value="addOrupdate" class="btn btn-primary" onclick="return addOrupdate();">Submit
        </button>
    </form>
</div>
</body>
</html>
