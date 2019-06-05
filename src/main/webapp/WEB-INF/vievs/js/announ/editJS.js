$(document).ready(function () {
    console.log("сработало");
    if ($("#author").val() === $("#log").val()) {
        disabledfalshe($("#buttonedit"), false);
    }
})

function enableall(param) {
    disabledfalshe($("#totalbutton"), param);
    var totalform = document.getElementById("totalform");
    ciclic(totalform.getElementsByTagName("input"), param);
    ciclic(totalform.getElementsByTagName("select"), param);
    ciclic(totalform.getElementsByTagName("textarea"), param);
}

function ciclic(tag, param) {
    for (let i = 0; i < tag.length; i++) {
        var id = tag[i].getAttribute("id");
        if (id !== "author" && id !=="created") {
            disabledfalshe($("#" + id + ""), param);
        }
    }
}

function disabledfalshe(pole, param) {
    pole.prop('disabled', param)
}