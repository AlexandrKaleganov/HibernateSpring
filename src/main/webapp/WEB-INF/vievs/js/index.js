$(document).ready(function anlist() {
    listan("findallan");
});

/**
 * скрипты для отрисовывания таблицы userlist
 */
function listan(actions) {
    $.ajax({
        type: "POST",
        url: "./",
        data: {action: actions, an: "{\"id\":\"0\"}", car: "{\"id\":\"0\"}"},
        dataType: "json",
        success: function (data) {
            console.log(data.length);
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                if (data[i].id > 0) {
                    $("#todolist_table tbody:last").append(loadtable(data[i]));
                }
            }
        }
    });
}

function loadtable(an) {
    var rsl = "";
    rsl = rsl + "<tr><td>" + an.id + "</td><td>" + an.name + "</td><td>" + an.created + "</td><td>" + an.author.name + "</td>";
    rsl = rsl + stringButton(an);
    return rsl;
}

function stringButton(an) {
    var rsl = "";
    if (an.done) {
        rsl = rsl + "<td><input type=\"checkbox\" disabled checked/></td>";
    } else {
        rsl = rsl + "<td><input type=\"checkbox\" disabled/></td>";
    }
    rsl = rsl + "<td><form action=\"${pageContext.servletContext.contextPath}/\" method=\"post\">\n" +
        "                            <input type=\"hidden\" name=\"an\" value=\"" + an.id + "\">\n" +
        "                            <input type=\"hidden\" name=\"action\" value=\"findbyidan\">\n" +
        "                            <input type=\"submit\" value=\"Просмотр\">\n" +
        "                        </form>" + "</td></tr>"
    return rsl;
}