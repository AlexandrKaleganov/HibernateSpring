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
        + "<a class=\"nav-link\" href=\"${pageContext.servletContext.contextPath}/api/users\">Список пользователей</a>\n"
        + "</li>\n"

        + "<li class=\"nav-item\">\n"
        + "<a class=\"nav-link\" href=\"${pageContext.servletContext.contextPath}/\" >Список объявлений</a>\n"
        + "</li>\n"

        + "   </ul>\n"
        + "    <a href=\"${pageContext.servletContext.contextPath}/login?logout\" type=\"button\" style=\"display: block; margin-left: auto;\" class=\"btn btn-outline-danger\" onclick=\"exit()\">ВЫХОД</a>\n"
        // + "    <form action=\"${pageContext.servletContext.contextPath}/logout\" method=\"post\">\n" +
        // "            <input type=\"submit\" value=\"Sign Out\"/>\n" +
        // "        </form>"
        + "</nav>";
    $("#navipanel").append(nbar);
    $("#username").append('  <sec:authorize access="isAuthenticated()">\n' +
        '        <sec:authentication property="principal.authorities"  var="authorities"/>\n' +
        '        <button id="log" class="btn btn-primary" style=" margin-left: auto;"\n' +
        '                value="<sec:authentication property="name"/>" type="button" disabled>\n' +
        '            <sec:authentication property="name"/></button>\n' +
        '        <sec:authentication property="principal.authorities" var="authorities" />\n' +
        '        <button id="rol" class="btn btn-primary" style="margin-left: auto;" value="${authorities[0].authority}" type="button"\n' +
        '                disabled>${authorities[0].authority}</button>\n' +
        '    </sec:authorize>')
});

function exit() {
    $.ajax({
        type: "POST",
        url: ${pageContext.servletContext.contextPath}"/logout",
        data: {exit: "exit"}
    })
}