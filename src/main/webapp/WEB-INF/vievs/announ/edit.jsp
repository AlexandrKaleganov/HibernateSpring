<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <script charset="UTF-8" type="text/javascript">
        <%@include file="/WEB-INF/vievs/js/navibar.js" %>
        <%@include file="/WEB-INF/vievs/js/announ/edit.js" %>
    </script>

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
    <form>
        <%--row 1--%>
        <div class="form-row">
            <%--left--%>
            <div class="form-group col-md-6">
                <div class="form-group">
                    <input type="hidden" class="form-control" id="idan" name="idan" value="${an.id}"
                           title="Enter ID an.">
                </div>
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${an.name}" title="Enter name"
                           placeholder="name">
                </div>
                <div class="form-group">
                    <label for="created">Create_Dat:</label>
                    <input type="text" class="form-control" name="password" value="${an.created}" title="Enter created."
                           id="created">
                </div>
                <div class="checkbox">
                    <label><input type="checkbox" name="isDone" id="isDone">Is_Done</label>
                </div>

            </div>
            <%--right--%>
            <div class="form-group col-md-6">
                <div class="form-group">
                    <input type="hidden" class="form-control" name="authorid" value="${an.author.id}"
                           title="Enter author id."
                           id="authorid">
                </div>
                <div class="form-group">
                    <label for="author">Author:</label>
                    <input type="text" class="form-control" name="login" value="${an.author.login}"
                           title="Enter author."
                           id="author">
                </div>

            </div>
        </div>

        <%--center--%>
        <div class="form-group">
            <input type="hidden" class="form-control" name="carid" value="${an.car.id}"
                   title="Enter car id."
                   id="carid">
        </div>
        <%--row 2--%>
        <div class="form-row">
            <%--left--%>
            <div class="form-group col-md-5">
                <label for="marka">Car marka:</label>
                <select class="form-control" name="marka" title="Enter marka." id="marka" onclick="true">
                    <option value="${an.car.model.marka.id}">${an.car.model.marka.name}</option>
                </select>
            </div>
            <%--center--%>
            <div class="form-group col-md-4">
                <label for="marka">Car model:</label>
                <select class="form-control" name="model" title="Enter model." id="model" onclick="true">
                    <option value="${an.car.model.id}">${an.car.model.name}</option>
                </select>
            </div>
                <div class="form-group col-md-3">
                    <label for="transmission">Car transmission:</label>
                    <select class="form-control" name="transmission" title="Enter transmission." id="transmission" onclick="true">
                        <option value="${an.car.transmission.id}">${an.car.transmission.name}</option>
                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label for="yar">Год выпуска:</label>
                    <select class="form-control" name="yar" title="Enter yar." id="yar" onclick="true">
                        <option value="${an.car.yar}">${an.car.yar}</option>
                    </select>
                </div>
<c:forEach items="${an.car.photo}" var="b">
                <div id="profileDiv" style="padding: 10px; border: solid 2px #D6D6D6;">
                    <img src="data:image/png;base64,"${b.photo}"
                         width="117" height="160"/>
                </div>
</c:forEach>
<%--                center--%>
        </div>

        <%--center--%>
        <div class="form-group">
            <label for="description">Car description:</label>
            <textarea class="form-control" id="description" rows="3" title="Enter car description.">${an.car.description}</textarea>
        </div>
</div>
<button type="submit" class="btn btn-primary">Sign in</button>
</form>
</div>
</body>
</html>
