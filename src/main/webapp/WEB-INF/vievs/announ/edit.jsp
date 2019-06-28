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
        <%@include file="/WEB-INF/vievs/js/announ/editJS.js" %>
    </script>

    <title>Добавление изменен</title>
</head>
<body>
<div id="navipanel">
</div>
<div class="username" id="username" style="float: right;">
</div>
<br/>
<br/>
<div id="result"></div>
<br/>
<div class="container" id="totalform">
    <div class="form-row">
        <div class="form-group col-md-6">
            <button id="buttonedit" type="button" class="btn btn-success" onclick="enableall(false)" disabled>
                Редактировать
            </button>
        </div>
        <div class="form-group col-md-6">
            <button id="totalbutton" type="button" value="addAn" class="btn btn-primary" onclick="addAnno()" disabled>
                Применить
            </button>
        </div>
        <div class="form-group col-md-6">
            <button id="deleteButton" type="button" value="addAn" class="btn btn-primary" onclick="deleteAn()" disabled>
                Удалить объявление полностью
            </button>
        </div>
    </div>
    <form>
        <div class="form-row">
            <div class="form-group col-md-6">
                <div class="form-group">
                    <input type="hidden" class="form-control" id="idan" name="idan" value="${an.id}"
                           title="Enter ID an.">
                </div>
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${an.name}" title="Enter name"
                           placeholder="name" disabled>
                </div>
                <div class="form-group">
                    <label for="created">Create_Dat:</label>
                    <input type="text" class="form-control" name="created" value="${an.created}" title="Enter created."
                           id="created" disabled>
                </div>
                <div class="checkbox" >
                    <c:choose>
                        <c:when test="${an.done == true}">
                            <label><input type="checkbox" id="isDone" name="isDone" disabled checked> Is_Done</label>
                        </c:when>
                        <c:otherwise>
                            <label><input type="checkbox" id="isDone" name="isDone"  disabled>Is_Done</label>
                        </c:otherwise>
                    </c:choose>

                </div>

            </div>
            <div class="form-group col-md-6">
                <div class="form-group">
                    <input type="hidden" class="form-control" name="authorid" value="${an.author.id}"
                           title="Enter author id."
                           id="authorid">
                </div>
                <div class="form-group">
                    <label for="author">Author:</label>
                    <input type="text" class="form-control" name="author" value="${an.author.login}"
                           title="Enter author."
                           id="author" disabled>
                </div>
            </div>
        </div>
        <div class="form-group">
            <input type="hidden" class="form-control" name="carid" value="${an.car.id}"
                   title="Enter car id."
                   id="carid">
        </div>
        <div class="form-row">
            <div class="form-group col-md-5">
                <label for="marka">Car marka:</label>
                <select class="form-control" name="marka" title="Enter marka." id="marka" onclick="modelload()"
                        disabled>
                    <option value="${an.car.model.marka.id}">${an.car.model.marka.name}</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="model">Car model:</label>
                <select class="form-control" name="model" title="Enter model." id="model" onclick="true" disabled>
                    <option value="${an.car.model.id}">${an.car.model.name}</option>
                </select>
            </div>
            <div class="form-group col-md-3">
                <label for="transmission">Car transmission:</label>
                <select class="form-control" name="transmission" title="Enter transmission." id="transmission"
                        onclick="true" disabled>
                    <option value="${an.car.transmission.id}">${an.car.transmission.name}</option>
                </select>
            </div>
            <div class="form-group col-md-3">
                <label for="yar">Год выпуска:</label>
                <select class="form-control" name="yar" title="Enter yar." id="yar" onclick="true" disabled>
                    <option value="${an.car.yar}">${an.car.yar}</option>
                </select>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6" id="imageupload">
                <form id="sampleUploadFrm" enctype="multipart/form-data" method="post">
                    <input id="filePhoto" type="file" name="file"/>
                    <input id="uploadButton" type="button" value="Отправить" onclick="fileupload()">
                </form>
            </div>
            <div class="form-group col-md-6" id="imageList">
            </div>
        </div>
        <div id="imageView">
            <c:forEach items="${an.car.photo}" var="photo">
                <img src="${pageContext.servletContext.contextPath}/image?id=${photo.id}" alt="..." width="600"
                     height="300">
                <button type="button" name="delBut" class="close" data-dismiss="alert" aria-label="Close" disabled \onclick="deleteAn()">&times;</button>
            </c:forEach>
        </div>
        <div class="form-group">
            <label for="description">Car description:</label>
            <textarea class="form-control" id="description" rows="3"
                      title="Enter car description." disabled>${an.car.description}</textarea>
        </div>
    </form>
</div>
</body>
</html>
