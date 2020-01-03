/**
 * скрипты для управления объявлением редактирование, удаление добавление,
 * отрисовки на странице edit.jsp
 */

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
        enableAll(false);
    }
});

/**
 * включение всех полей
 * и подгрузка года, марки и трансмиссии
 * @param param
 */
function enableAll(param) {
    disabledfalshe($("#totalbutton"), param);
    var totalform = document.getElementById("totalform");
    ciclic(totalform.getElementsByTagName("input"), param);
    ciclic(totalform.getElementsByTagName("select"), param);
    ciclic(totalform.getElementsByTagName("textarea"), param);
    disabledtag(document.getElementsByName("delBut"), param);
    disabledfalshe($("#deleteButton"), param);
    loadyar();
    markload();
    transmissionload();
    disabledfalshe($("#buttonedit"), true);
}

/**
 * отключение полей
 * @param param
 */
function dissabl(param) {
    disabledfalshe($("#deleteButton"), param);
    disabledfalshe($("#totalbutton"), param);
    var totalform = document.getElementById("totalform");
    ciclic(totalform.getElementsByTagName("input"), param);
    ciclic(totalform.getElementsByTagName("select"), param);
    ciclic(totalform.getElementsByTagName("textarea"), param);
    disabledtag(document.getElementsByName("delBut"), param);
    disabledfalshe($("#buttonedit"), false);
    disabledfalshe($("#uploadButton"), param);
    disabledtag(document.getElementsByName("isDone"), param);
    // document.getElementsByName("delBut").removeAttribute("disabled");
    // disabledtag(, param);

}

/**
 * отключение включение по тегам
 * @param tag список тегов которые необходимо отключить
 * @param param параметр включения/отключения
 */
function disabledtag(tag, param) {
    if (param) {
        for (var i = 0; i < tag.length; i++) {
            tag[i].disabled = true;
        }
    } else {
        for (var i = 0; i < tag.length; i++) {
            tag[i].removeAttribute('disabled');
        }
    }
}

/**
 * включение или отключение всех полей по указанному тегу
 * @param tag
 * @param param
 */
function ciclic(tag, param) {
    for (var i = 0; i < tag.length; i++) {
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
    pole.prop('disabled', param);
}

/**
 * загрузка года выпуска автомобилей
 */
function loadyar() {
    for (var i = 2000; i < 2020; i++) {
        var res = " <option value=\"";
        res = res + i;
        res = res + "\">" + i + "</option>";
        $("#yar").append(res);
    }
}

/**
 * получение списка марок автомобилей
 */
function markload() {
    $.ajax({
        type: "POST",
        url: "../markLoad",
        data: {action: "findAllmark", m: "{\"id\":\"" + $("#mark").val() + "\"" + "}"},
        dataType: "json",
        success: function (data) {
            ciclappendoption($("#mark"), data);

        }
    });
}

/**
 * получение списка моделей
 */
function modelload() {
    $.ajax({
        type: "POST",
        url: "../modelLoad",
        data: {action: "findBymarkIdModel", m: "{\"id\":\"" + $("#mark").val() + "\"" + "}"},
        dataType: "json",
        success: function (data) {
            ciclappendoption($("#model"), data);
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

/**
 * получение списка трансмиссии
 */
function transmissionload() {
    $.ajax({
        contentType: 'application/json; charset=utf-8',
        type: "GET",
        url: "../transmission",
        data: {action: "findAllTr", tr: "{\"id\":\"" + $("#transmission").val() + "\"" + "}"},
        dataType: "json",
        success: function (data) {
            ciclappendoption($("#transmission"), data);
        }
    });
}

/**
 * валидация что поля заполнены
 * @param r
 * @param l
 * @returns {boolean}
 */
function isValid(r, l) {
    var rsl = false;
    if (r.val() === l) {
        rsl = true;
        alert(r.attr("title"));
    }
    return rsl;
}

function valid() {
    return !(isValid($("#name"), "") + isValid($("#mark"), "") + isValid($("#model"), "")
        + isValid($("#transmission"), "") + isValid($("#yar"), "") + isValid($("#description"), ""));
}

/**
 * добавление объявления, после того как оно будет добавлено,
 */
function addAnno() {
    var rsl = "";
    if ($("#idan").val() > 0) {
        rsl = "обновлён";
    } else {
        rsl = "добавлен";
    }
    if (valid()) {
        $.ajax({
            type: "POST",
            url: "../edit",
            data: {
                action: $("#totalbutton").val(),
                an: toReceiveTheAnnouncementFromAForm(),
                car: toReceiveTheCarFromAForm()
            },
            dataType: "json",
            success: function (data) {
                $("#result").html("");
                $("#result").append("<div class=\"alert alert-success  alert-dismissible\">\n" +
                    "            " + data.name + " <strong> " + rsl + "</strong>\n" +
                    "        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">&times;</button>");
                photoDel("0", data.id, "command");

                //очистка фотографий в сессии
                managementOfPhotosInASession("clearPhList", "0");
                //включение кнопки редактирование
                disabledfalshe($("#buttonedit"), false);

            }
        });
        //отключение всех элементовпереводим в состаяние disabled
        dissabl(true);
        return true;
    } else {
        return false;
    }
}

/**
 * метод для сбора информации об автомобиле
 * @returns {string}
 */
function toReceiveTheCarFromAForm() {
    return "{\"id\":\"" + $("#carid").val() + "\"" +
        ", \"model\":{\"id\":\"" + $("#model").val() + "\"" + "}," + "\"yar\":\"" + $("#yar").val() + "\"" +
        ", \"transmission\":{\"id\":\"" + $("#transmission").val() + "\"}" +
        ", \"description\":\"" + $("#description").val() + "\"" + "}";
}

function toReceiveTheAnnouncementFromAForm() {
    return "{\"id\":\"" + $("#idan").val() + "\", \"name\":\"" + $("#name").val() + "\", \"done\":\"" + $("#isDone").is(":checked") +
        "\", \"author\":{\"id\":\"" + $("#authorid").val() + "\"}" + "}";
}

//Start Photo
/**
 * загрузка файлов на сервлет после возвращает из сесии список загруженных фото
 * эти фотографии  не отрисовываются а только выходит список фотографии в сессии
 * с индексами чтобы можно было удалить фото из сессии
 */
function fileupload() {
    var form = new FormData();
    form.append('file', $('#filePhoto')[0].files[0]);
    jQuery.ajax({
        url: '../upload',
        type: "POST",
        data: form,
        processData: false,
        contentType: false,
        success: function (result) {
            $('#imageList').html("");
            $("#imageList").append(returnHeadTable());
            for (var i = 0; i < result.length; i++) {
                $('#imageList tbody:last').append(returnTable(i));
            }
        }
    });
}


/**
 * метод принимает команду что необходимо сделать с фотографией
 * @param idPhoto фотографии метод после получает список фотографий и отрисовывает фото заново
 * @param idAn id объявления
 * @param action команда
 */
function photoDel(idPhoto, idAn, action) {
    jQuery.ajax({
        url: '../image',
        type: "POST",
        data: {idPhoto: idPhoto, idAn: idAn, action: action},
        success: function (data) {
            toDrawAPhoto(data.car.photo);
        }
    });
}

/**
 * отрисовка фотографий
 */
function toDrawAPhoto(photoList) {
    $("#imageView").html("");
    for (var i = 0; i < photoList.length; i++) {
        $("#imageView").append("<img src=\"${pageContext.servletContext.contextPath}/image?id=" + photoList[i].id + "\" alt=\"...\" width=\"600\"\n" +
            "                     height=\"300\">" +
            " <button type=\"button\" value=\"" + photoList[i].id + "\" name=\"delBut\" class=\"close\" data-dismiss=\"alert\"\n" +
            "                        aria-label=\"Close\" disabled onclick=\"photoDel(this.value, $('#idan').val(), 'delete')\">&times;\n" +
            "                </button>");
    }
}

/**
 *
 * @param param1 команда, что необходимо надо будет сделать с фотографией в сессии удалить добавить или очистить все
 * @param param2 индекс, если будем удалять конкртную фотографию из сессии то будет прилетать индекс
 */
function managementOfPhotosInASession(param1, param2) {
    jQuery.ajax({
        url: '../sessionPhotos',
        type: "POST",
        data: {action: param1, index: param2},
        success: function (result) {
            $('#imageList').html("");
            $("#imageList").append(returnHeadTable());
            for (var i = 0; i < result.length; i++) {
                $('#imageList tbody:last').append(returnTable(i));
            }
        }
    });
}

/**
 * отрисовка таблицы со списком отправленных фотографий в сессии
 * @param i индекс фотографии в листе
 * @returns {string} возвращает готоавую строку с кнопкой для возможности удалить из сессии фотографию
 */
function returnTable(i) {
    return "    <tr>\n" +
        "      <th scope=\"row\">" + i + "</th>\n" +
        "      <td> image__" + i + "</td>\n" +
        "      <td><button type=\"button\" class=\"close\" value=\"" + i + "\" data-dismiss=\"alert\" aria-label=\"Close\"     onclick=\"managementOfPhotosInASession('delete', this.value)\">&times;</button></td>\n\n" +
        "    </tr>\n";
}

/**
 *
 * @returns {string} получение шапки таблицы со списком загруенных в сессию фотографий
 */
function returnHeadTable() {
    return "<table class=\"table\">\n" +
        "  <thead>\n" +
        "    <tr>\n" +
        "      <th scope=\"col\">#</th>\n" +
        "      <th scope=\"col\">Фото</th>\n" +
        "      <th scope=\"col\">Удалить</th>\n" +
        "    </tr>\n" +
        "  </thead>\n" +
        "  <tbody>\n" +
        "  </tbody>\n" +
        "</table>";
}

//End Photo
/**
 * удалить объявление полностью
 */
function deleteAn() {
    $.ajax({
        type: "POST",
        url: "./delete",
        data: {
            action: "deleteAn",
            an: toReceiveTheAnnouncementFromAForm(),
            car: toReceiveTheCarFromAForm()
        }
    });
}

// загрузка файлов на сервлет второй вариант оба варианта рабочие от случая к случаю
// function fileupload() {
//     var url = "./upload";
//     var form = $("#sampleUploadFrm")[0];
//     var data = new FormData(form);
//     $.ajax({
//         type: "POST",
//         encType: "multipart/form-data",
//         url: url,
//         cache: false,
//         processData: false,
//         contentType: false,
//         data: data,
//         success: function (msg) {
//             console.log(msg);
//             var status = msg.status;
//             if (status === 1) {
//                 alert("File has been uploaded successfully");
//             } else {
//                 alert("Couldn't upload file");
//             }
//         },
//         error : function(msg) {
//             alert("Couldn't upload file");
//         }
//     });
// }
