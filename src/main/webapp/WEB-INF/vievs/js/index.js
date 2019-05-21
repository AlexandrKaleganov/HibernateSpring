﻿$(document).ready(function anlist() {
    listan("findAllAn");
});

/**
 * скрипты для отрисовывания таблицы списка объявлений
 */
function listan(actions) {
    $.ajax({
        type: "POST",
        url: "./",
        data: {action: actions, an: "{\"id\":\"0\"}", car: "{\"id\":\"0\"}"},
        dataType: "json",
        success: function (data) {
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
    rsl = rsl + "<tr class='table-data'><td>" + an.id + "</td><td>" + an.name + "</td><td>" + an.created + "</td><td>" + an.car.model.marka.name + "</td>";
    rsl = rsl + stringButton(an);
    return rsl;
}

function stringButton(an) {
    var rsl = "";
    rsl = rsl + "<td>" + an.car.model.name + "</td><td>" + an.car.yar + "</td><td>" + an.author.name + "</td>";
    if (an.done) {
        rsl = rsl + "<td><input type=\"checkbox\" disabled checked/></td>";
    } else {
        rsl = rsl + "<td><input type=\"checkbox\" disabled/></td>";
    }
    rsl = rsl + "<td><form action=\"${pageContext.servletContext.contextPath}/\" method=\"post\">\n" +
        "                            <input type=\"hidden\" name=\"an\" value=\"" + an.id + "\">\n" +
        "                            <input type=\"hidden\" name=\"action\" value=\"findByIdAn\">\n" +
        "                            <input type=\"submit\" value=\"Просмотр\">\n" +
        "                        </form>" + "</td></tr>";
    return rsl;
}