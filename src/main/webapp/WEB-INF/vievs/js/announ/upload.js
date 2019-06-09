// $(document).ready(function() {
//     function bs_input_file() {
//         $(".input-file").before(
//             function() {
//                 if ( ! $(this).prev().hasClass('input-ghost') ) {
//                     var element = $("<input type='file' class='input-ghost' style='visibility:hidden; height:0'>");
//                     element.attr("name",$(this).attr("name"));
//                     element.change(function(){
//                         element.next(element).find('input').val((element.val()).split('\\').pop());
//                     });
//                     $(this).find("button.btn-choose").click(function(){
//                         element.click();
//                     });
//                     $(this).find("button.btn-reset").click(function(){
//                         element.val(null);
//                         $(this).parents(".input-file").find('input').val('');
//                     });
//                     $(this).find('input').css("cursor","pointer");
//                     $(this).find('input').mousedown(function() {
//                         $(this).parents('.input-file').prev().click();
//                         return false;
//                     });
//                     return element;
//                 }
//             }
//         );
//     }
//
//     bs_input_file();
//
//     $("#uploadBtn").on("click", function() {
//         var url = "./upload";
//         var form = $("#sampleUploadFrm")[0];
//         var data = new FormData(form);
//         /* var data = {};
//         data['key1'] = 'value1';
//         data['key2'] = 'value2'; */
//         $.ajax({
//             type : "POST",
//             encType : "multipart/form-data",
//             url : url,
//             cache : false,
//             processData : false,
//             contentType : false,
//             data : data,
//             success : function(msg) {
//                 var response = JSON.parse(msg);
//                 var status = response.status;
//                 if (status == 1) {
//                     alert("File has been uploaded successfully");
//                 } else {
//                     alert("Couldn't upload file");
//                 }
//             },
//             error : function(msg) {
//                 alert("Couldn't upload file");
//             }
//         });
//     });
// });