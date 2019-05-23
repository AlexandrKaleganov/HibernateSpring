$(document).ready(function () {
    var nbar = "<nav class=\"navbar navbar-expand-sm bg-dark navbar-dark\">\n"
        + "    <a class=\"navbar-brand\"href=\"${pageContext.servletContext.contextPath}/\">Logo</a>\n"
        + "<ul class=\"navbar-nav\">\n"

        + "<li class=\"nav-item\">\n"
        + "<a class=\"nav-link\" href=\"${pageContext.servletContext.contextPath}/create\">Добавить пользователя</a>\n"
        + "</li>\n"

        + "<li class=\"nav-item\">\n"
        + "<a class=\"nav-link\" href=\"${pageContext.servletContext.contextPath}/listUser\">Список пользователей</a>\n"
        + "</li>\n"

        + "   </ul>\n"
        + "    <a href=\"${pageContext.servletContext.contextPath}/\" type=\"button\" style=\"display: block; margin-left: auto;\" class=\"btn btn-outline-danger\" onclick=\"exit()\">ВЫХОД</a>\n"
        + "</nav>";
    console.log(nbar);
    $("#body div").append(nbar);
});

function exit() {
    $.ajax({
        type: "POST",
        url: "./",
        data: {exit: "exit"},
    })
};