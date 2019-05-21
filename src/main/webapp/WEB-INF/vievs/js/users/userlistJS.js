$(document).ready(function () {
        listuser();
});

/**
 * скрипты для отрисовывания таблицы userlist
 */
function listuser() {
    $.ajax({
        type: "POST",
        url: "./listUser",
        data: {action: "getListUser", us: "{\"login\":\"login\"" + "}"},
        dataType: "json",
        success: function (data) {
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
    var rsl = " <form action=\"${pageContext.servletContext.contextPath}/listUser\" method=\"post\">\n" +
        "                            <input type=\"hidden\" name=\"us\" value=\"" + id + "\">\n" +
        "                            <input type=\"hidden\" name=\"action\" value=\"findByIdUser\">\n" +
        "                            <input type=\"submit\" value=\"EDIT\">\n" +
        "                        </form>" + "</td><td>" +
        "                        <form action=\"${pageContext.servletContext.contextPath}/listUser\" method=\"post\">\n" +
        "                            <input type=\"hidden\" name=\"us\" value=\"" + id + "\">\n" +
        "                            <input type=\"hidden\" name=\"action\" value=\"deleteUser\">\n" +
        "                            <input type=\"submit\" value=\"DELETE\">\n" +
        "                       </form>" + "" +
        "</td></tr>";
    return rsl;
}
function jsonId(id) {
    var rsl = "{\"id\":\"" + id + "\"}";
    return rsl;
}
