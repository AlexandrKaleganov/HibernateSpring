<%--
  Created by IntelliJ IDEA.
  User: USERCRB001
  Date: 21.05.2019
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>авторизация</title>
</head>
<body>

<c:if test="${param.error!=null}">
    <div class="alert alert-danger  alert-dismissible">
        <strong>System error:</strong>     Invalid username and password.
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">&times;</button>
    </div>
</c:if>
<c:if test="${param.logout!=null}">
    <div class="alert alert-danger  alert-dismissible">
        <strong>System error:</strong>  You have been logged out.
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">&times;</button>
    </div>
</c:if>
<div class="container">
    <h2>Авторизация</h2>
    <form action="${pageContext.servletContext.contextPath}/login" class="needs-validation" method="post">
        <sec:csrfInput />
        <div class="form-group">
            <label for="uname">Username:</label>
            <input type="text" class="form-control" id="uname" placeholder="Enter username" name="username" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
       <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
