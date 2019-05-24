$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "./listUser",
        data: {action: "getListUser", us: "user"},
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                console.log(data[i]);
            }
        }
    })
})