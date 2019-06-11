/**
 * загрузка всего листа
 */
$(document).ready(function () {
    todolist("list");
    backet("1", "findallmap");
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
            console.log(data);
            for (var i = 0; i < data.length; i++) {
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
    rsl = rsl + "<td><button type=\"button\" value=\"" + data.id + "\" class=\"btn btn-primary\" onclick=\"backet(this.value, 'add')\" >добавить в корзину</button></td>";
    rsl = rsl + "<td><button type=\"button\" name=\"delone\" value=\"" + data.id + "\" class=\"btn btn-success\" onclick=\"backet(this.value, 'delone')\" disabled>удалить из корзины</button></td></tr>";
    return rsl;
}


