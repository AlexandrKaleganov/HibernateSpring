/**
 * в данном скрипте идёт отрисовка навигационной панели, кнопки выхода, и полей с отображением текущего пользователя
 * логина и пароля для корректной отрисовки на jsp должны присутствовать два блока div id="navipanel" id="username"
 */
$(document).ready(function () {
    // language=HTML
    var nbar = "<nav class=\"navbar navbar-expand-sm bg-dark navbar-dark\">\n"
        + "    <a class=\"navbar-brand\" href=\"${pageContext.servletContext.contextPath}/\">Logo</a>\n"
        + "<ul class=\"navbar-nav\">\n"

        + "<li class=\"nav-item\">\n"
        + "<a class=\"nav-link\" href=\"${pageContext.servletContext.contextPath}/listUser\">Список пользователей</a>\n"
        + "</li>\n"

        + "<li class=\"nav-item\">\n"
        + "<a class=\"nav-link\" href=\"${pageContext.servletContext.contextPath}/\" >Список объявлений</a>\n"
        + "</li>\n"

        + "   </ul>\n"
        + "    <a href=\"${pageContext.servletContext.contextPath}/\" type=\"button\" style=\"display: block; margin-left: auto;\" class=\"btn btn-outline-danger\" onclick=\"exit()\">ВЫХОД</a>\n"
        + "</nav>";
    $("#navipanel").append(nbar);
    $("#username").append(" <button  id=\"log\"  class=\"btn btn-primary\" style=\" margin-left: auto;\" value=\"${login}\" type=\"button\" disabled>${login}</button>\n" +
        "    <button id=\"rol\" class=\"btn btn-primary\" style=\"margin-left: auto;\" value=\"${role}\" type=\"button\" disabled>${role}</button>");
});

function exit() {
    $.ajax({
        type: "POST",
        url: "./",
        data: {exit: "exit"}
    })
}