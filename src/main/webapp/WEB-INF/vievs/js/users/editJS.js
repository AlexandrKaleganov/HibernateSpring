/**
 * скрипты для управления пользователями
 */

/**
 *
 * @param r поле
 * @param l параметр с которым необходимо сравнить
 * @returns {boolean} исли параметр совпал с полем, то вернёт true
 */
function isvalId(r, l) {
    var rsl = false;
    if (r.val() === l) {
        rsl = true;
        alert(r.attr("title"));
    }
    return rsl;
}

/**
 * метод обажается к полям по id  и проверяет что они не пустые
 * @returns {boolean} если всё хорошо то вернёт true
 */
function valId() {
    return !(isvalId($("#name"), "") + isvalId($("#login"), "") + isvalId($("#password"), "")
        + isvalId($("#roles"), ""));
}

/**
 * ретурн ролелист старт
 */
$(document).ready(function rolelist() {
    $.ajax({
        type: "POST",
        url: "./listRoles",
        data: {action: "findAllRoles", ro: "{\"id\":\"0\"" + "}"},
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#roles option:last").after(returnrolelist(data[i]));
            }
        }
    });

    function returnrolelist(user) {
        // noinspection JSJQueryEfficiency
        if ($("#rol").val() === "ADMIN") {
            return "<option value='" + user.id + "'>" + user.role + "</option>"
        } else if ($("#rol").val() === user.role) {
            return "<option value='" + user.id + "'>" + user.role + "</option>"
        } else {
            return "";
        }
    }
});

/**
 * метод добавление или изменение пользователя
 * @returns {boolean} если все поля в пользователе прошли проверку, то вернёт true иначе false
 */
function addOrupdate() {
    var rsl = "";
    var action = "";
    // noinspection JSJQueryEfficiency
    if ($("#id").val() > 0) {
        rsl = "обновлён";
        action = "addOrupdate";
    } else {
        rsl = "добавлен";
        action = "addus"
    }
    if (valId()) {
        $.ajax({
            type: "POST",
            url: "./listUser",
            data: {
                action: action,
                us: "{\"id\":\"" + $("#id").val() + "\", \"name\":\"" + $("#name").val() + "\", \"login\":\"" + $("#login").val() +
                    "\", \"password\":\"" + $("#password").val() + "\", \"roles\":{\"id\":\"" + $("#roles").val() + "\"}}"
            },
            dataType: "json",
            success: function (data) {
                if (data.id !== 0) {
                    // noinspection JSUnresolvedVariable,JSUnresolvedVariable
                    $("#result").after("<div class=\"alert alert-success  alert-dismissible\">\n" +
                        "            " + data.login + " <strong> " + rsl + "</strong>\n" +
                        "        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">&times;</button>");
                } else {
                    // noinspection JSUnresolvedVariable
                    $("#result").after("<div class=\"alert alert-success  alert-danger\">\n" +
                        "            " + data.login + " <strong> " + "пользователь с таким логином уже существует" + "</strong>\n" +
                        "        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">&times;</button>");
                }
            }

        });
        return true;
    } else {
        return false;
    }
}
