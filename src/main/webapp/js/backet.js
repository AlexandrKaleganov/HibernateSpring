/**
 * скрипт для работы с корзиной
 */


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
            disabledfalshe($("#buttonscoop"), true);
            for (var key in data) {
                disabledfalshe($("#buttonscoop"), false);
                $("#backet_table tbody:last").append(loadbacket(key, data[key]));
            }
            disable();
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
    rsl = rsl + "<td><button type=\"button\"  value=\"" + v.id + "\" class=\"btn btn-primary\" onclick=\"backet(this.value, 'deleteitemforkey')\" >Удалить позицию полностью</button></td></tr>";
    return rsl;
}

/**
 * получение json объекта из ключа
 * @param key
 * @returns {any}
 */
function getkey(key) {
    var str = '' + key.toString() + '';
    return JSON.parse(str);
}

/**
 * включение/отклчение кнопки удаления одной позиции
 */
function disable() {
    var trprice = document.getElementsByTagName("tbody")[1].getElementsByTagName("tr");
    var trbacket = document.getElementsByTagName("tbody")[0].getElementsByTagName("tr");
    for (j = 0; j < trprice.length; j++) {
        if (!(trbacket.length === 0)) {
            for (var i = 0; i < trbacket.length; i++) {
                if (trprice[j].getElementsByTagName("button")[1].value === trbacket[i].getElementsByTagName("button")[0].value) {
                    trprice[j].getElementsByTagName("button")[1].removeAttribute('disabled');
                    break;
                } else {
                    trprice[j].getElementsByTagName("button")[1].disabled = true;
                }
            }
        } else {
            trprice[j].getElementsByTagName("button")[1].disabled = true;
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
