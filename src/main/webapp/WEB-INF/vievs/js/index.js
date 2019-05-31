$(document).ready(function anlist() {
    $.ajax({
        type: "POST",
        url: "./",
        data: {action: "findallan", an: "{\"id\":\"0\"" + "}"},
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                console.log(data[i]);
            }
        }
    })
});