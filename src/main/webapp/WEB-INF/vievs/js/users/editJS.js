function isValid(r, l) {
    var rsl = false;
    if (r.val() == l) {
        rsl = true;
        alert(r.attr("title"));
    }
    return rsl;
};

function valid() {
    return !(isValid($("#name"), "") + isValid($("#login"), "") + isValid($("#password"), "")
        + isValid($("#roles"), ""));
};

function rolelist() {
    $.ajax({
        type: "POST",
        url: "./listRoles",
        data: {action: "findallroles", ro: "{\"id\":\"0\"" + "}"},
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#roles option:last").after("<option value='" + data[i].id + "'>" + data[i].role + "</option>");
            }
        }
    })
};
$(document).ready(function () {
    if ($("#rol").val() === "ADMIN") {
        rolelist();
    }
});

function addOrupdate() {
    var rsl = "";
    if ($("#id").val() > 0) {
        rsl = "обновлён";
    } else {
        rsl = "добавлен";
    }
    if (valid()) {
        $.ajax({
            type: "POST",
            url: "./listUser",
            data: {
                action: $("#action").val(),
                us: "{\"id\":\"" + $("#id").val() + "\", \"name\":\"" + $("#name").val() + "\", \"login\":\"" + $("#login").val() +
                    "\", \"password\":\"" + $("#password").val() + "\", \"roles\":{\"id\":\"" + $("#roles").val() + "\"}}"
            },
            dataType: "json",
            success: function (data) {
                console.log(data);
                    $("#result").after("<div class=\"alert alert-success  alert-dismissible\">\n" +
                        "            " + data.login  + " <strong> " + rsl + "</strong>\n" +
                        "        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">&times;</button>");
            }
        })
        return true;
    } else {
        return false;
    }
};
