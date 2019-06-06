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

function enableall(param) {
    disabledfalshe($("#totalbutton"), param);
    var totalform = document.getElementById("totalform");
    ciclic(totalform.getElementsByTagName("input"), param);
    ciclic(totalform.getElementsByTagName("select"), param);
    ciclic(totalform.getElementsByTagName("textarea"), param);
    loadyar();
    marka("findallmarka");
    disabledfalshe($("#buttonedit"),true);
}
function dissabl(param) {
    disabledfalshe($("#totalbutton"), param);
    var totalform = document.getElementById("totalform");
    ciclic(totalform.getElementsByTagName("input"), param);
    ciclic(totalform.getElementsByTagName("select"), param);
    ciclic(totalform.getElementsByTagName("textarea"), param);
    disabledfalshe($("#buttonedit"),false);
}
function ciclic(tag, param) {
    for (let i = 0; i < tag.length; i++) {
        var id = tag[i].getAttribute("id");
        if (id !== "author" && id !== "created") {
            disabledfalshe($("#" + id + ""), param);
        }
    }
}

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

function marka() {
    $.ajax({
        type: "POST",
        url: "./markaload",
        data: {action: "findallmarka", m: "{\"id\":\"" +  $("#marka").val() + "\"" + "}"},
        dataType: "json",
        success: function (data) {
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                var r = option(data[i]);
                console.log(r);
                $("#marka").append(r);
            }
        }
    })
}

function modelload() {
    console.log($("#marka").val());
    $.ajax({
        type: "POST",
        url: "./markaload",
        data: {action: "findByMarkaidModel", m: "{\"id\":\"" + $("#marka").val() + "\"" + "}"},
        dataType: "json",
        success: function (data) {
            console.log(data);
            $("#model").html("");
            for (var i = 0; i < data.length; i++) {
                var r = option(data[i]);
                console.log(r)
                $("#model").append(r);
            }
        }
    })
}

function option(ma) {
    console.log(ma.id);
    console.log(ma.name);
    var res = " <option value=\""
    res = res + ma.id;
    res = res + "\">" + ma.name + "</option>";
    return res;
}