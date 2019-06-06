$(document).ready(function () {
    /**
     * просмотр объявления,
     * если объявление подал текущий автор,
     * то становится доступна кнопка редактирование
     * при нажатие на кнопку редактирование , кнопка становится неактивна,
     * а все поля редактирования становятся активными
     */
    if ($("#author").val() === $("#log").val()) {
        disabledfalshe($("#buttonedit"), false);
    }
    /**
     * если у объявления нету id т.е. мы зашли на вкладку добавления
     * становятся активными поля для подачи объявления
     * и в качестве автора из сессии проставляются данные текущего пользователя
     */
    if ($("#idan").val() < 1) {
        enableall(false);
    }
});

/**
 * включение всех полей
 * и подгрузка года, марки и трансмиссии
 * @param param
 */
function enableall(param) {
    disabledfalshe($("#totalbutton"), param);
    var totalform = document.getElementById("totalform");
    ciclic(totalform.getElementsByTagName("input"), param);
    ciclic(totalform.getElementsByTagName("select"), param);
    ciclic(totalform.getElementsByTagName("textarea"), param);
    loadyar();
    markaload();
    transmissionload()
    disabledfalshe($("#buttonedit"), true);
}

/**
 * отключение полей
 * @param param
 */
function dissabl(param) {
    disabledfalshe($("#totalbutton"), param);
    var totalform = document.getElementById("totalform");
    ciclic(totalform.getElementsByTagName("input"), param);
    ciclic(totalform.getElementsByTagName("select"), param);
    ciclic(totalform.getElementsByTagName("textarea"), param);
    disabledfalshe($("#buttonedit"), false);
}

/**
 * включение или отключение всех полей по указанному тегу
 * @param tag
 * @param param
 */
function ciclic(tag, param) {
    for (let i = 0; i < tag.length; i++) {
        var id = tag[i].getAttribute("id");
        if (id !== "author" && id !== "created") {
            disabledfalshe($("#" + id + ""), param);
        }
    }
}

/**
 * отключение конкретного поля
 * @param pole
 * @param param
 */
function disabledfalshe(pole, param) {
    pole.prop('disabled', param)
}

/**
 * загрузка года
 * @param id
 */
function loadyar() {
    for (var i = 2000; i < 2020; i++) {
        var res = " <option value=\""
        res = res + i;
        res = res + "\">" + i + "</option>";
        $("#yar").append(res);
    }
}

/**
 * получение списка марок автомобилей
 */
function markaload() {
    $.ajax({
        type: "POST",
        url: "./markaload",
        data: {action: "findallmarka", m: "{\"id\":\"" + $("#marka").val() + "\"" + "}"},
        dataType: "json",
        success: function (data) {
            ciclappendoption($("#marka"), data);

        }
    })
}

/**
 * получение списка моделей
 */
function modelload() {
    $.ajax({
        type: "POST",
        url: "./markaload",
        data: {action: "findByMarkaidModel", m: "{\"id\":\"" + $("#marka").val() + "\"" + "}"},
        dataType: "json",
        success: function (data) {
            ciclappendoption($("#model"), data);
        }
    })
}

/**
 * рефакторинг прорисовки optional
 * @param ma
 * @returns {string}
 */
function ciclappendoption(id, data) {
   id.html("");
    for (var i = 0; i < data.length; i++) {
       id.append(option(data[i]));
    }
}
function option(ma) {
    var res = " <option value=\""
    res = res + ma.id;
    res = res + "\">" + ma.name + "</option>";
    return res;
}

/**
 * получение списка трансмиссии
 */
function transmissionload() {
    $.ajax({
        type: "POST",
        url: "./transmission",
        data: {action: "findalltr", tr: "{\"id\":\"" + $("#transmission").val() + "\"" + "}"},
        dataType: "json",
        success: function (data) {
            ciclappendoption($("#transmission"), data);
        }
    })
}

/**
 * валидация что поля заполнены
 * @param r
 * @param l
 * @returns {boolean}
 */
function isValid(r, l) {
    var rsl = false;
    if (r.val() == l) {
        rsl = true;
        alert(r.attr("title"));
    }
    return rsl;
}
function valid() {
    return !(isValid($("#name"), "") + isValid($("#marka"), "") + isValid($("#model"), "")
        + isValid($("#transmission"), "") + isValid($("#yar"), "") + isValid($("#description"), ""));
}

/**
 * добавление объявления
 */
function addAnno() {
    var rsl = "";
    if ($("#id").val() > 0) {
        rsl = "обновлён";
    } else {
        rsl = "добавлен";
    }
    console.log(valid());
    if (valid()) {
        $.ajax({
            type: "POST",
            url: "./",
            data: {
                action: $("#totalbutton").val(),
                us: "{\"id\":\"" + $("#id").val() + "\", \"name\":\"" + $("#name").val() + "\", \"done\":\"" + $("#isDone").is(":checked") +
                   "\", \"author\":{\"id\":\"" + $("#authorid").val() + "\"}}"
            },
            dataType: "json",
            success: function (data) {
                console.log(data);
                $("#result").after("<div class=\"alert alert-success  alert-dismissible\">\n" +
                    "            " + data.login + " <strong> " + rsl + "</strong>\n" +
                    "        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">&times;</button>");
            }
        })
        return true;
    } else {
        return false;
    }
}