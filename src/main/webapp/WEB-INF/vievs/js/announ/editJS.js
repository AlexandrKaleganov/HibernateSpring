$(document).ready(function () {
    console.log("сработало");
    if ($("#author").val() === $("#log").val()) {
        disabledfalshe($("#buttonedit"));
    }
})

function enableall() {
    disabledfalshe($("#totalbutton"));
    var totalform = document.getElementById("totalform");
    ciclic(totalform.getElementsByTagName("input"));
    ciclic(totalform.getElementsByTagName("select"));
    ciclic(totalform.getElementsByTagName("textarea"));
}

function ciclic(tag) {
    for (let i = 0; i < tag.length; i++) {
        var id = tag[i].getAttribute("id");
        if (id !== "author" && id !=="created") {
            disabledfalshe($("#" + id + ""));
        }
    }
}

function disabledfalshe(pole) {
    pole.prop('disabled', false)
}