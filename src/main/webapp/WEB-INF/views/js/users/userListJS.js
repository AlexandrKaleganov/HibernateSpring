$(document).ready(function () {
        listuser();
});

/**
 * скрипты для отрисовывания таблицы userlist
 */
function listuser() {
    //как в этом методе получить пользователей?
    $.ajax({
        type: "get",
        url: "./users/list",
        // data: {action: "getListUser", us: "{\"login\":\"login\"" + "}"},
        // dataType: "json",
        success: function (data) {
            console.log(data);
console.log(data.length);
            for (var i = 0; i < data.length; i++) {
                $("#todolist_table tbody:last").append(loadtable(data[i]));
            }
        }
    });
}

function loadtable(u) {
    var rsl = "";
    rsl = rsl + "<tr><td>" + u.id + "</td><td>" + u.name + "</td><td>" + u.login + "</td><td>" + u.roles.role + "</td><td>";
    if ($("#rol").val() === "ADMIN") {
        rsl = rsl + stringButton(u.id);

    } else if ($("#log").val() === u.login){
        rsl = rsl + stringButton(u.id);
    } else {
        rsl = rsl + "</td><td></td></tr>"
    }
    return rsl;
}

function stringButton(id) {
    var rsl = "  <a href=\"${pageContext.servletContext.contextPath}/api/users/findById/"+ id+ "\"type=\"button\" style=\"display: block; margin-left: auto;\" class=\"btn btn-outline-success\">ПРОСМОТР</a>"
        + "</td><td>" +
       "<a href=\"${pageContext.servletContext.contextPath}/api/users/deleteUser/"+ id+ "\"type=\"button\" style=\"display: block; margin-left: auto;\" class=\"btn btn-outline-success\">Удалить</a>"+
        "</td></tr>";
    return rsl;
}
function jsonId(id) {
    var rsl = "{\"id\":\"" + id + "\"}";
    return rsl;
}
