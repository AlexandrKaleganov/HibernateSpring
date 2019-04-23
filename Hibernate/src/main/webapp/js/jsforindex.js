/**
 * загрузка всего листа
 */
$(document).ready(function () {
    todolist("list");
});

/**
 * загрузка листа всего или только невыполненных
 * я не фильтрую готовый список а делаю запрос в базу и hibernate  сразу мне возвращает либо полный список, либо не
 * выполненные, по умолчанию чек включен чтобы показывать все итемы по этому по умолчанию тянутся все заявки
 */
function todolistNotDone() {
    $("#todolist_table tbody").html("");
    if ($("#check1").is(":checked")) {
        todolist("list");
    } else {
        todolist("listnotDone")
    }
}

/**
 * метод получает команду и отпрвляет запрос на сервлет и получает либо полный список,
 * либо только невыполненные заявки
 * @param action
 */
function todolist(action) {
    $.ajax({
        type: "GET",
        url: "./todolist",
        data: {action: action},
        success: function (data) {
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                console.log(data[i]);
                $("#todolist_table tbody:last").append(loadtable(data[i]));
            }
        }
    });
};

/**
 * метод добавления заявко в таблицу
 * @param data
 * @returns {string}
 */
function loadtable(data) {
    console.log(data);
    var rsl = "";
    rsl = rsl + "<tr><td>" + data.id + "</td><td>" + data.descr + "</td><td>" + data.create + "</td>";
    if (data.done) {
        rsl = rsl + "<td><input type=\"checkbox\" disabled checked/></td>";
    } else {
        rsl = rsl + "<td><input type=\"checkbox\" disabled/></td></tr>";
    }
    return rsl;
}

/**
 * метод добавления заявки, добавляет сразу выполненныю или не выполненную,
 * чтобы потом тестировать фильтр
 */
function addDesc() {
    if (isDesc($("#descr"))) {
        $.ajax({
            type: "POST",
            url: "./todolist",
            data: {action: $("#action").val(), descr: $("#descr").val(), done: $("#isDone").is(":checked")},
            success: function (data) {
                $("#todolist_table tbody").append(loadtable(data));
            }
        });
    }
}

/**
 * проверяет введён ли description
 * @param desc
 * @returns {boolean}
 */
function isDesc(desc) {
    var test = /^[^\s]*$/;
    console.log(desc.val().length);
    if (desc.val().length < 1 || !test.test(desc.val())) {
        alert($("#descr").attr("placeholder"));
        return false;
    } else {
        console.log("заявка добавлена");
        return true;
    }
};