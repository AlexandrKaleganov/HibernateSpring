/**
 * добавление итема по id  и получение списка всех купленных товаров
 * @param id
 */
function backet(id, action) {
    $.ajax({
        type: "POST",
        url: "./backet",
        data: {action: action, id: id},
        dataType: 'json',
        success: function (data) {
            $("#backet_table tbody").html("");

            for (var key in data) {
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
    rsl = rsl + "<td><button type=\"button\" value=\"" + v.id + "\" class=\"btn btn-primary\" onclick=\"backet(this.value, 'deleteitemforkey')\" >Удалить позицию полностью</button></td></tr>";
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