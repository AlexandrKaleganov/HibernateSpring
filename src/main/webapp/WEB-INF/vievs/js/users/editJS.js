﻿function isValid(r, l) {
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
/**
 * ретурн ролелист старт
 */
$(document).ready(function rolelist() {
    $.ajax({
        type: "POST",
        url: "./listRoles",
        data: {action: "findallroles", ro: "{\"id\":\"0\"" + "}"},
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#roles option:last").after(returnrolelist(data[i]));
            }
        }
    });

    function returnrolelist(user) {
        if ($("#rol").val() === "ADMIN") {
            return "<option value='" + user.id + "'>" + user.role + "</option>"
        } else if ($("#rol").val() === user.role) {
            return "<option value='" + user.id + "'>" + user.role + "</option>"
        } else {
            return "";
        }
    }
});


function addOrupdate() {
    var rsl = "";
    if ($("#id").val() > 0) {
        rsl = "обновлён";
    } else {
        rsl = "добавлен";
    }
    var is = validLogin();
    console.log(is);
    if (valid() && is) {
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
                    "            " + data.login + " <strong> " + rsl + "</strong>\n" +
                    "        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">&times;</button>");
            }
        })
        return true;
    } else {
        return false;
    }
}

function validLogin() {
    var rsl = true;
    if (!($("#id").val() > 0)) {
        $.ajax({
            type: "POST",
            url: "./listUser",
            data: {
                action: "findByLogin",
                us: "{\"id\":\"" + $("#id").val() + "\", \"name\":\"" + $("#name").val() + "\", \"login\":\"" + $("#login").val() +
                    "\", \"password\":\"" + $("#password").val() + "\", \"roles\":{\"id\":\"" + $("#roles").val() + "\"}}"
            },
            dataType: "json",
            success: function (data) {
                console.log(data.login);
                if (data.login === $("#login").val()) {
                    $("#result").after("<div class=\"alert alert-success  alert-dismissible\">\n" +
                        "            " + data.login + " <strong> " + "пользователь с таким логином уже существует" + "</strong>\n" +
                        "        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">&times;</button>");
                    rsl = false;
                }
            }

        })
    }
    return rsl;
}