data.mathematicalSymbol = undefined;

/**
 * метод формирует объект Task  и отправляет запрос на сервлет
 * после получает результат и выводит на страницу
 */
function inputTask() {
    // noinspection JSUnusedGlobalSymbols
    $.ajax({
        type: "POST",
        url: "./voice",
        data: {
            task: "{" +
                "\"x\":\"" + $("#inputX").val() + "\"," +
                " \"y\":\"" + $("#inputY").val() + "\"," +
                " \"mathematicalSymbol\":\"" + $("#mathematicalSymbol").val() + "\"}"
        },
        dataType: 'json',
        success: function (data) {
            // noinspection JSJQueryEfficiency
            $("#result").html("");
            // noinspection JSJQueryEfficiency
            $("#result").append("<div class=\"alert alert-success\" >\n" +
                "<strong>" + data.x + " " + data.mathematicalSymbol + " " + data.y + " = " + data.result + "</strong> </div> ");
        }
    });
}

