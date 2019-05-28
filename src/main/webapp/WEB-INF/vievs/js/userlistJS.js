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
            console.log(data.length);
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                $("#todolist_table tbody:last").append(loadtable(data[i]));
            }
        }
    });
}

function user(userLogin) {
    $.ajax({
        type: "POST",
        url: "./listUser",
        data: {action: "findByLogin", us: "{\"login\":\"" + userLogin + "\"" + "}"},
        dataType: "json",
        success: function (data) {
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                $("#todolist_table tbody:last").append(loadtable(data[i]));
            }
        }
    });
}

function loadtable(u) {
    var rsl = "";
    rsl = rsl + "<tr><td>" + u.id + "</td><td>" + u.name + "</td><td>" + u.login + "</td><td>" + u.roles.role + "</td><td>";
    if ($("#rol").eq("ADMIN")) {
        rsl = rsl + " <form action=\"${pageContext.servletContext.contextPath}/listUser\" method=\"post\">\n" +
            "                            <input type=\"hidden\" name=\"id\" value=\"" + u.id + "\">\n" +
            "                            <input type=\"hidden\" name=\"action\" value=\"findbyiduser\">\n" +
            "                            <input type=\"submit\" value=\"EDIT\">\n" +
            "                        </form>" + "</td><td>" +
            "                        <form action=\"${pageContext.servletContext.contextPath}/listUser\" method=\"post\">\n" +
            "                            <input type=\"hidden\" name=\"us\" value=\"" + u.id + "\">\n" +
            "                            <input type=\"hidden\" name=\"action\" value=\"deleteuser\">\n" +
            "                            <input type=\"submit\" value=\"DELETE\">\n" +
            "                       </form>" + "" +
            "</td></tr>";
    } else {
        rsl = rsl + "</td><td></td></tr>"
    }
    return rsl;
}

function jsonId(id) {
    var rsl =  "{\"id\":\"" + id + "\"}";
    return rsl;
}
