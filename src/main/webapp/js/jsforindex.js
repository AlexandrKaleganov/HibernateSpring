/**
 * загрузка всего листа
 */
$(document).ready(function () {
    todolist("list");
});

/**
 * метод получает команду и отпрвляет запрос на сервлет и получает полный список
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
 * метод отрисовки прайслиста
 * @param data
 * @returns {string}
 */
function loadtable(data) {
    var rsl = "";
    rsl = rsl + "<tr><td>" + data.id + "</td><td>" + data.name + "</td><td>" + data.price + "</td>";
    rsl = rsl + "<td><button type=\"button\" value=\"" + data.id + "\" class=\"btn btn-primary\" onclick=\"abbclick(this.value)\" >добавить в корзину</button></td>";
    rsl = rsl + "<td><button type=\"button\" value=\"" + data.id + "\" class=\"btn btn-primary\" onclick=\"deleteone(this.value)\" >удалить из корзины</button></td></tr>";
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
 * удаление одной одного товара из позиции
 * @param id
 */
function deleteone(id) {
    $.ajax({
        type: "POST",
        url: "./backet",
        data: {action: "delone", id: id},
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
 * удаление одной одного товара из позиции
 * @param id
 */
function deleteitemforkey(id) {
    $.ajax({
        type: "POST",
        url: "./backet",
        data: {action: "deleteitemforkey", id: id},
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
 * метод отвечает за покупку
 * @param val
 */
function scope(val) {
    $.ajax({
        type: "GET",
        url: "./backet",
        dataType: 'json',
        success: function (data) {
            $("#scope").html("");
            $("#scope").append("<p>Счёт:</p>\n" +
                "        <table class=\"table table-striped\" id=\"table_scope\">\n" +
                "            <thead class=\"thead-dark\">\n" +
                "            <tr>\n" +
                "                <th>ID &darr;</th>\n" +
                "                <th>Название &darr;</th>\n" +
                "                <th>Цена &darr;</th>\n" +
                "                <th>Сумма за позицию &darr;</th>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody></tbody>\n" +
                "        </table>");
            $("#table_scope tbody").html("");
            var summ = 0;
            for (var key in data) {
                console.log(key + ' ' + data[key]);
                var str = '' + key.toString() + '';
                var v = JSON.parse(str);
                summ = summ + v.price * data[key];
                $("#table_scope tbody:last").append(loadscope(v, data[key]));
            }
            $("#table_scope tbody:last").append("<tr><td>ИТОГО К ОПЛАТЕ</td><td></td><td></td><td>" + summ + "</td></tr>");
            clearmap();
        }
    });
}

/**
 * отрисовка счёта
 */
function loadscope(key, dat) {
    var rsl = "";
    rsl = rsl + "<tr><td>" + key.id + "</td><td>" + key.name + "</td><td>" + key.price + "</td><td>" + key.price * dat + "</td></tr>";
    return rsl;
}

/**
 * отрисовка корзины
 */
function loadbacket(key, dat) {
    var str = '' + key.toString() + '';
    var v = JSON.parse(str);
    var rsl = "";
    rsl = rsl + "<tr><td>" + v.name + "</td><td>" + dat + "</td>";
    rsl = rsl + "<td><button type=\"button\" value=\"" + v.id + "\" class=\"btn btn-primary\" onclick=\"deleteitemforkey(this.value)\" >Удалить позицию полностью</button></td></tr>";
    return rsl;
}

/**
 * очистка хешмапы в сессии
 */
function clearmap() {
    $.ajax({
        type: "POST",
        url: "./backet",
        data: {action: "clear", id:"1"},
        dataType: 'json',
        success: function () {
            $("#backet_table tbody").html("");
        }
    });
}