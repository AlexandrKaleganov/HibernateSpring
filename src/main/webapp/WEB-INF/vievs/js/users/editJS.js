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
        url: "./stringlist",
        data: {action: "findallroles", ro: "{\"id\":\"0\"" + "}"},
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#roles option:last").after("<option value='" + data[i].id + "'>" + data[i].role + "</option>");
            }
        }
    })
};
$(document).ready(function () {
    console.log($("#rol").val()==="ADMIN");
       console.log();
    // if ($("#rol").val().contains("ADMIN")){
    //     rolelist();
    // }
})