/**
 * скрипт для работы с корзиной
 */


/**
 * добавление итема по id  и получение списка всех купленных товаров
 * @param id тега
 * @param action  действие выполняемое при нажатии кнопки
 */
function basket(id, action) {
    $.ajax({
        type: "POST",
        url: "./basket",
        data: {action: action, id: id},
        dataType: 'json',
        success: function (data) {
            $("#basket_table tbody").html("");
            // noinspection JSJQueryEfficiency
            disabledfalshe($("#buttonscoop"), true);
            for (var key in data) {
                disabledfalshe($("#buttonscoop"), false);
                // noinspection JSUnfilteredForInLoop
                $("#basket_table tbody:last").append(loadbasket(key, data[key]));
            }
            disable();
        }
    });
}

/**
 * отрисовка корзины
 */
function loadbasket(key, dat) {
    var str = '' + key.toString() + '';
    var v = JSON.parse(str);
    var rsl = "";
    rsl = rsl + "<tr><td>" + v.name + "</td><td>" + dat + "</td>";
    rsl = rsl + "<td><button type=\"button\"  value=\"" + v.id + "\" class=\"btn btn-primary\" onclick=\"basket(this.value, 'deleteItemForKey')\" >Удалить позицию полностью</button></td></tr>";
    return rsl;
}
//
// /**
//  * получение json объекта из ключа
//  * @param key
//  * @returns {any}
//  */
// function getkey(key) {
//     var str = '' + key.toString() + '';
//     return JSON.parse(str);
// }

/**
 * включение/отклчение кнопки удаления одной позиции
 */
function disable() {
    var trprice = document.getElementsByTagName("tbody")[1].getElementsByTagName("tr");
    var trbasket = document.getElementsByTagName("tbody")[0].getElementsByTagName("tr");
    for (var j = 0; j < trprice.length; j++) {
        if (!(trbasket.length === 0)) {
            for (var i = 0; i < trbasket.length; i++) {
                if (trprice[j].getElementsByTagName("button")[1].value === trbasket[i].getElementsByTagName("button")[0].value) {
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
 * @param pole поле по id  которое необходимо отключеить/включить
 * @param param булеан параметр отключение включения поля
 */
function disabledfalshe(pole, param) {
    pole.prop('disabled', param)
}
