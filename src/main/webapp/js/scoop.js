/**
 * метод отвечает за покупку
 */
function scope() {
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
                "                <th>Количество &darr;</th>\n" +
                "                <th>Сумма за позицию &darr;</th>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody></tbody>\n" +
                "        </table>");
            $("#table_scope tbody").html("");
            var summmoney = 0;
            var summcount = 0;
            for (var key in data) {
                console.log(key + ' ' + data[key]);
                var str = '' + key.toString() + '';
                var v = JSON.parse(str);
                summmoney = summmoney + v.price * data[key];
                summcount = summcount + data[key];
                $("#table_scope tbody:last").append(loadscope(v, data[key]));
            }
            $("#table_scope tbody:last").append("<tr><td>ИТОГО К ОПЛАТЕ</td><td></td><td></td><td>" + summcount + " </td><td>" + summmoney + "</td></tr>");
            backet("1", "clear");
            $("#scope").append("<button type=\"button\" class=\"btn btn-success\" onclick=\"clearscoop()\">Очистить счёт</button>");
        }
    });
}

/**
 * отрисовка счёта
 */
function loadscope(key, dat) {
    var rsl = "";
    rsl = rsl + "<tr><td>" + key.id + "</td><td>" + key.name + "</td><td>" + key.price + "</td><td>" + dat + "</td><td>" + key.price * dat + "</td></tr>";
    return rsl;
}

/**
 * метод очистки счёта
 */
function clearscoop() {
    $("#scope").html("");
}