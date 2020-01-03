$(document).ready(function anlist() {
    listan("findAllAn");
});

/**
 * скрипты для отрисовывания таблицы списка объявлений
 */
function listan(actions) {
    $.ajax({
        type: "GET",
        url: "./listAn",
        data: {action: actions, an: "{\"id\":\"0\"}", car: "{\"id\":\"0\"}"},
        dataType: "json",
        success: function (data) {
            $("#todolist_table tbody").html("");
            for (var i = 0; i < data.length; i++) {
                if (data[i].id > 0) {
                    $("#todolist_table tbody:last").append(loadtable(data[i]));
                }
            }
        }
    });
}

/**
 * скрипты для отрисовывания таблицы списка объявлений
 */
function addOrUpdate() {
    $.ajax({
        type: "GET",
        url: "./listAn",
        data: {action: actions, an: "{\"id\":\"0\"}", car: "{\"id\":\"0\"}"},
        dataType: "json",
        success: function (data) {
            $("#todolist_table tbody").html("");
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
    rsl = rsl + "<tr class='table-data'><td>" + an.id + "</td><td>" + an.name + "</td><td>" + an.created + "</td><td>" + an.car.model.mark.name + "</td>";
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
    rsl = rsl + "<td>" +
        "  <a href=\"${pageContext.servletContext.contextPath}/findById/" + an.id + "\"type=\"button\" style=\"display: block; margin-left: auto;\" class=\"btn btn-outline-success\">ПРОСМОТР</a>"
        + "</td></tr>";
    return rsl;
}

function filterAction() {
    var toShowACertainBrand = "toShowACertainBrand";
    var toShowForTheLastDay = "toShowForTheLastDay";
    var toShowWithAPhoto = "toShowWithAPhoto";
    console.log($("#filter").val());
    console.log("  $(\"#filter\").val() === toShowACertainBrand");
    console.log($("#filter").val() === "toShowACertainBrand");
    console.log($("#filter").val().length);
    if ($("#filter").val().length > 0) {
        console.log("фильтр не пустой");
        console.log($("#mark").val());
        if (($("#filter").val() === "toShowACertainBrand" && $("#mark").val() === "0")) {
            console.log("зашли в метод загрузки списка марок");
            document.getElementById("mark").removeAttribute("hidden");
            this.markload();
        } else {
            if ($("#filter").val() === "toShowForTheLastDay" || $("#filter").val() === "toShowWithAPhoto") {
                document.getElementById("mark").setAttribute("hidden", true);
                $("#mark").html("");
                $("#mark").append("<option value=\"0\"></option>");
            }
            $.ajax({
                type: "GET",
                url: "./filterAn",
                data: {mark: $("#mark").val(), param: $("#filter").val()},
                dataType: "json",
                success: function (data) {
                    $("#todolist_table tbody").html("");
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].id > 0) {
                            $("#todolist_table tbody:last").append(loadtable(data[i]));
                        }
                    }
                }
            });
        }
    } else {
        document.getElementById("mark").setAttribute("hidden", true);
        this.listan("findAllAn");
    }
}

/**
 * получение списка марок автомобилей
 */
function markload() {
    $.ajax({
        type: "POST",
        url: "./markLoad",
        data: {action: "findAllmark", m: "{\"id\":\"" + $("#mark").val() + "\"" + "}"},
        dataType: "json",
        success: function (data) {
            ciclappendoption($("#mark"), data);

        }
    });
}

/**
 * рефакторинг прорисовки optional
 * @returns {string}
 * @param id  в качестве параметра указывается id  тега оптионала
 * @param data  сюда данные для прорисовки
 */
function ciclappendoption(id, data) {
    id.html("");
    id.append("<option value=\"0\"></option>");
    for (var i = 0; i < data.length; i++) {
        id.append(option(data[i]));
    }
}

/**
 * собирается опция
 * @param ob объект из списка по которому будет собираца опция (модель, марка, год, трансмиссия)
 * @returns {string}  возвращает готовую проприсованную опцию в виде строки
 */
function option(ob) {
    var res = " <option value=\"";
    res = res + ob.id;
    res = res + "\">" + ob.name + "</option>";
    return res;
}