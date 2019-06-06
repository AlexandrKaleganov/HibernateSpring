$(document).ready(function () {
    console.log("сработало");
    console.log( $("#idan").val());
    console.log( $("#idan").val().length);

    if ($("#author").val() === $("#log").val()) {
        disabledfalshe($("#buttonedit"), false);
    }
    if ($("#idan").val().length < 1) {
        enableall(false);
    }
});

function enableall(param) {
    disabledfalshe($("#totalbutton"), param);
    var totalform = document.getElementById("totalform");
    ciclic(totalform.getElementsByTagName("input"), param);
    ciclic(totalform.getElementsByTagName("select"), param);
    ciclic(totalform.getElementsByTagName("textarea"), param);
    load("yar", "command");
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

function load(id, command) {
    var idd = $("#" + id + "");
    if (id!=="yar") {
        ciclic(idd, command)
    } else{
        for (var i = 2000; i < 2020; i++) {
            var res = " <option value=\""
            res= res + ${i};
            res = res + "\">i</option>";
            idd.append(res);
        }
    }
   }

   function ciclload(id, command) {
       $.ajax({
           type: "POST",
           url: "./carload",
           data: {action: "findallroles", ro: "{\"id\":\"0\"" + "}"},
           dataType: "json",
           success: function (data) {
               for (var i = 0; i < data.length; i++) {
                   $("#roles option:last").after(returnrolelist(data[i]));
               }
           }
       })
   }