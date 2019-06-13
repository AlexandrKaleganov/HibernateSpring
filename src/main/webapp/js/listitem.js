/**
 * загрузка всего листа
 */
$(document).ready(function () {
    todolist("list");
    basket("1", "findAllMap");
});

/**
 * получить прайс лист
 * @param action
 */
function todolist(action) {
    $.ajax({
        type: "GET",
        url: "./todolist",
        data: {action: action},
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#todolist_table tbody:last").append(loadtable(data[i]));
            }
        }
    });
}

/**
 * метод отрисовки прайслиста
 * @param data
 * @returns {string}
 */
function loadtable(data) {
    var rsl = "";
    // noinspection JSUnresolvedVariable
    rsl = rsl + "<tr><td>" + data.id + "</td><td>" + data.name + "</td><td>" + data.price + "</td>";
    rsl = rsl + "<td><button type=\"button\" value=\"" + data.id + "\" class=\"btn btn-primary\" onclick=\"basket(this.value, 'add')\" >добавить в корзину</button></td>";
    rsl = rsl + "<td><button type=\"button\" name=\"delone\" value=\"" + data.id + "\" class=\"btn btn-success\" onclick=\"basket(this.value, 'delOne')\" disabled>удалить из корзины</button></td></tr>";
    return rsl;
}


