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
 * удаление одной позиции по id
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