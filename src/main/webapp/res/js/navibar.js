$(document).ready(function () {
    var  nbar = "<nav class=\"navbar navbar-expand-sm bg-dark navbar-dark\">\n";
    nbar = nbar + "    <a class=\"navbar-brand\"href=\"${pageContext.servletContext.contextPath}/\">Logo </a>\n";
    nbar = nbar + "<ul class=\"navbar-nav\">\n";

    nbar = nbar +"<li class=\"nav-item\">\n";
    nbar = nbar +"<a class=\"nav-link\" href=\"${pageContext.servletContext.contextPath}/create\">Добавить пользователя</a>\n";
    nbar = nbar +"</li>\n";

    nbar = nbar +"<li class=\"nav-item\">\n";
    nbar = nbar +"<a class=\"nav-link\" href=\"${pageContext.servletContext.contextPath}/listUser\">Список пользователей</a>\n";
    nbar = nbar +"</li>\n";

    nbar = nbar +"   </ul>\n";
    nbar = nbar +"    <button type=\"submit\" style=\"display: block; margin-left: auto;\" class=\"btn btn-outline-danger\" onclick=\"exit()\">ВЫХОД</button>\n" ;
    nbar = nbar + "</nav>";
    console.log(nbar);
    $("#body div").append(nbar);
});
function exit() {
    $.ajax({
        type: "POST",
        url: "./",
        data: {exit: "exit"}
    })
};
