<%--
  Created by IntelliJ IDEA.
  User: Lis
  Date: 30 мая 19
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление изменен</title>
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
            <select class="form-control" name="roles" title="Enter attribut dostupa." id="roles">
                <option value="${user.roles.id}">${user.roles.role}</option>
            </select>
        </div>
        <button type="button" id="action" name="action" value="addOrupdate" class="btn btn-primary" onclick="return addOrupdate();">Submit
        </button>
    </form>
</div>
</body>
</html>
