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
    dissabl(true);
    var rsl = "";
    if ($("#idan").val() > 0) {
        rsl = "обновлён";
    } else {
        rsl = "добавлен";
    }
    if (valid()) {
        $.ajax({
            type: "POST",
            url: "./",
            data: {
                action: $("#totalbutton").val(),
                an: "{\"id\":\"" + $("#idan").val() + "\", \"name\":\"" + $("#name").val() + "\", \"done\":\"" + $("#isDone").is(":checked") +
                    "\", \"author\":{\"id\":\"" + $("#authorid").val() + "\"}" + "}",
                car: "{\"id\":\"" + $("#carid").val() + "\"" +
                    ", \"model\":{\"id\":\"" + $("#model").val() + "\"" + "}," + "\"yar\":\"" + $("#yar").val() + "\"" +
                    ", \"transmission\":{\"id\":\"" + $("#transmission").val() + "\"}" +
                    ", \"description\":\"" + $("#description").val() + "\"" + "}",
                photolist: "[" + "\"db/Avito-Shema.png\"," + "\"db/Avito-Shema.png\"" + "]"
            },
            dataType: "json",
            success: function (data) {
                console.log(data);
                $("#result").after("<div class=\"alert alert-success  alert-dismissible\">\n" +
                    "            " + data + " <strong> " + rsl + "</strong>\n" +
                    "        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">&times;</button>");
            }
        })
        return true;
    } else {
        return false;
    }
}

//загрузка файлов на сервлет
function fileupload() {
    var url = "./upload";
    var form = $("#sampleUploadFrm");
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        encType: "multipart/form-data",
        url: url,
        cache: false,
        processData: false,
        contentType: false,
        data: data,
        success: function (msg) {
            console.log(msg);
            var status = msg.status;
            if (status == 1) {
                alert("File has been uploaded successfully");
            } else {
                alert("Couldn't upload file");
            }
        },
        error : function(msg) {
            alert("Couldn't upload file");
        }
    });
};
