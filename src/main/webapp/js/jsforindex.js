/**
 * загрузка всего листа
 */
$(document).ready(function () {
    todolist("list");
});

// /**
//  * загрузка листа всего или только невыполненных
//  * я не фильтрую готовый список а делаю запрос в базу и hibernate  сразу мне возвращает либо полный список, либо не
//  * выполненные, по умолчанию чек включен чтобы показывать все итемы по этому по умолчанию тянутся все заявки
//  */
// function todolistNotDone() {
//     $("#todolist_table tbody").html("");
//     if ($("#check1").is(":checked")) {
//         todolist("list");
//     } else {
//         todolist("listnotDone")
//     }
// }

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
    var rsl = "";
    rsl = rsl + "<tr><td>" + data.id + "</td><td>" + data.name + "</td><td>" + data.price + "</td>";
    rsl = rsl + "<td><button type=\"button\" value=\"" + data.id + "\" class=\"btn btn-primary\" onclick=\"abbclick(this.value)\" >добавить в корзину</button></td>";
    rsl = rsl + "<td><button type=\"button\" value=\"" + data.id + "\" class=\"btn btn-primary\" onclick=\"additem()\" disabled>удалить из корзины</button></td></tr>";
    return rsl;
}

/**
 * добавление итема по id  и получение списка всех купленных товаров
 * @param id
 */
function abbclick(id) {
    $.ajax({
        type: "POST",
        url: "./backet",
        data: {action: "add", id: id},
        dataType: 'json',
        success: function (data) {
            $("#backet_table tbody").html("");
                for (var key in data) {
                    console.log(key + ' ' + data[key]);
                    $("#backet_table tbody:last").append(loadbacket(key, data[key]));
                }
        }
    });
}

/**
 * отрисовка корзины
 */
function loadbacket(key, dat) {
    console.log(key);
    var v = JSON.parse(key.responseText);
    var rsl = "";
    rsl = rsl + "<tr><td>" + v.name + "</td><td>" + dat + "</td>" ;
    rsl = rsl + "<td><button type=\"button\" value=\"" + key.id + "\" class=\"btn btn-primary\" onclick=\"abbclick(this.value)\" >Удалить позицию полностью</button></td></tr>";
    return rsl;
}

