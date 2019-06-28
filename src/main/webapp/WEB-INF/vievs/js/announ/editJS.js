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
$(document).ready(function () {
    var  ann = JSON.parse('${an}');
    this.readAn(ann);
});

/**
 * отрисовка объявления
 */
function readAn(an) {
    $("#name").val(an.name);
}
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
    transmissionload();
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
    pole.prop('disabled', param)
}

/**
 * загрузка года
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
function markaload() {
    $.ajax({
        type: "POST",
        url: "./markaload",
        data: {action: "findAllMarka", m: "{\"id\":\"" + $("#marka").val() + "\"" + "}"},
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
        data: {action: "findByMarkaIdModel", m: "{\"id\":\"" + $("#marka").val() + "\"" + "}"},
        dataType: "json",
        success: function (data) {
            ciclappendoption($("#model"), data);
        }
    })
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
        type: "POST",
        url: "./transmission",
        data: {action: "findAllTr", tr: "{\"id\":\"" + $("#transmission").val() + "\"" + "}"},
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
    if (r.val() === l) {
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
                    ", \"description\":\"" + $("#description").val() + "\"" + "}"
                // ,
                // photolist: "[" + "\"db/Avito-Shema.png\"," + "\"db/Avito-Shema.png\"" + "]"
            },
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data));
                $("#result").after("<div class=\"alert alert-success  alert-dismissible\">\n" +
                    "            " + data.name + " <strong> " + rsl + "</strong>\n" +
                    "        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">&times;</button>");
            }
        });
        return true;
        dissabl(true);
    } else {
        return false;
    }
}


/**
 * загрузка файлов на сервлет после возвращает из сесии список загруженных фото
 * эти фотографии  не отрисовываются а только выходит список фотографии в сессии
 * с индексами чтобы можно было удалить фото из сессии
 */
function fileupload() {
    var form = new FormData();
    form.append('file', $('#filePhoto')[0].files[0]);
    jQuery.ajax({
        url: './upload',
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
 *
 * @param param1 команда, что необходимо надо будет сделать с фотографией в сессии удалить добавить или очистить все
 * @param param2 индекс, если будем удалять конкртную фотографию из сессии то будет прилетать индекс
 */
function managementOfPhotosInASession(param1, param2) {
    console.log(param1);
    console.log(param2);

    jQuery.ajax({
        url: './sessionPhotos',
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
