/**
 * @author csl
 * @time 08/04/2017 15:34
 * @desc
 */

window.onload=function () {
   SwissKnife.$id("form-sa-btn-submit").addEventListener("click",onSubmit);
}

function onSubmit() {
   var email= SwissKnife.$id("form-sa-email").value;
   if(email.length == 0)return;
   if(email.indexOf("@")==-1)return;
   SwissKnife.$id("form-sa").submit();
}

var SwissKnife={
        $class: function (className) {
            return document.getElementsByClassName(className);
        },
        $id: function (elementId) {
            return document.getElementById(elementId);
        },
}
