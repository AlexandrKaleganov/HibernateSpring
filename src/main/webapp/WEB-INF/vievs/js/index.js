$(document).ready(function anlist() {
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
        console.log($("#marka").val());
        if (($("#filter").val() === "toShowACertainBrand" && $("#marka").val() === "0")) {
            console.log("зашли в метод загрузки списка марок");
            document.getElementById("marka").removeAttribute("hidden");
            this.markaload();
        } else {
            if ($("#filter").val() === "toShowForTheLastDay" || $("#filter").val() === "toShowWithAPhoto") {
                $("#marka").hidden = true;
                $("#marka").html("");
                $("#marka").append("<option value=\"0\"></option>");
            }
            $.ajax({
                type: "POST",
                url: "./",
                data: {action: "filter", an: "{\"id\":\"0\"}", marka: "{\"id\":\""+$("#marka").val()+"\"}", param: $("#filter").val()},
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
        this.listan("findAllAn");
    }
}

/**
 * получение списка марок автомобилей
 */
function markaload() {
    $.ajax({
        type: "POST",
        url: "./markaload",
        data: {action: "findAllMarka", m: "{\"id\":\"" + $("#marka").val() + "\"" + "}"},
        dataType: "json",
        success: function (data) {
            ciclappendoption($("#marka"), data);

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