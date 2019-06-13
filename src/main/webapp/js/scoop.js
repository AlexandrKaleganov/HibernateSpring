/**
 * метод отвечает за покупку
 */
function scope() {
    $.ajax({
        type: "GET",
        url: "./basket",
        dataType: 'json',
        success: function (data) {
            // noinspection JSJQueryEfficiency
            $("#scope").html("");
            // noinspection JSJQueryEfficiency
            $("#scope").append("<p>Счёт:</p>\n" +
                "        <table class=\"table table-striped\" id=\"table_scope\">\n" +
                "            <thead class=\"thead-dark\">\n" +
                "            <tr>\n" +
                "                <th>ID </th>\n" +
                "                <th>Название </th>\n" +
                "                <th>Цена </th>\n" +
                "                <th>Количество </th>\n" +
                "                <th>Сумма за позицию </th>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody></tbody>\n" +
                "        </table>");
            $("#table_scope tbody").html("");
            var summmoney = 0;
            var summcount = 0;
            for (var key in data) {
                // noinspection JSUnfilteredForInLoop
                var str = '' + key.toString() + '';
                var v = JSON.parse(str);
                // noinspection JSUnresolvedVariable
                // noinspection JSUnfilteredForInLoop
                summmoney = summmoney + v.price * data[key];
                // noinspection JSUnfilteredForInLoop
                summcount = summcount + data[key];
                // noinspection JSUnfilteredForInLoop
                $("#table_scope tbody:last").append(loadscope(v, data[key]));
            }
            // noinspection JSJQueryEfficiency
            $("#table_scope tbody:last").append("<tr><td>ИТОГО К ОПЛАТЕ</td><td></td><td></td><td>" + summcount + " </td><td>" + summmoney + "</td></tr>");
            basket("1", "clear");
            $("#scope").append("<button type=\"button\" class=\"btn btn-success\" onclick=\"clearscoop()\">Очистить счёт</button>");
        }
    });
}

/**
 * отрисовка счёта
 */
function loadscope(key, dat) {
    var rsl = "";
    // noinspection JSUnresolvedVariable
    rsl = rsl + "<tr><td>" + key.id + "</td><td>" + key.name + "</td><td>" + key.price + "</td><td>" + dat + "</td><td>" + key.price * dat + "</td></tr>";
    return rsl;
}

/**
 * метод очистки счёта
 */
function clearscoop() {
    $("#scope").html("");
}