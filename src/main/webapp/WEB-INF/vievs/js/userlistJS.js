function listuser(){
    $.ajax({
        type: "POST",
        url: "./listUser",
        data: {action: "getListUser", us:"{\"login\":\"login\"" + "}"},
        success: function (data) {
            console.log(data.length);
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                console.log(data[i].login);
            }
        }
    });
}

function user(userLogin) {
    $.ajax({
        type: "POST",
        url: "./listUser",
        data: {action: "findByLogin", us:"{\"login\":\"" + userLogin + "\"" +"}"},
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                console.log(data[i].login);
            }
        }
    });
}