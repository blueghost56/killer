   var token=$("meta[name='_csrf']").attr("content");
   var header=$("meta[name='_csrf_header']").attr("content");
   $(document).ajaxSend(function (e,xhr,options) {
      xhr.setRequestHeader(header,token);
   });

// HttpUtils - post,get by promise
   var HttpUtils={
       promisePost:function (url,formId) {
          return ajaxPost(url,formId);
       }
   };

   var MessageUtils={
       sucess:function (msg) {
           showNotification(msg,NotificationStatus.Success,NotificationStyle.Direction.BottomLeft);
       },
       primary:function (msg) {
           showNotification(msg,NotificationStyle.Status.Primary,NotificationStyle.Direction.BottomLeft);
       },
       warning:function (msg) {
           showNotification(msg,NotificationStyle.Status.Warning,NotificationStyle.Direction.BottomLeft);
       },
       danger:function (msg) {
           showNotification(msg,NotificationStyle.Status.Danger,NotificationStyle.Direction.BottomLeft);
       }

   };

   // notification style and position enum
   var NotificationStyle={
       Status: {
           Success: 'success',
           Primary: 'primary',
           Warning: 'warning',
           Danger: 'danger'
       },
       Direction: {
           TopLeft: 'top-left',
           TopCenter: 'top-center',
           TopRight: 'top-right',
           BottomLeft: 'bottom-left',
           BottomCenter: 'bottom-center',
           BottomRight: 'bottom-right'
       }
   };

   function showNotification(msg,style,dir) {
      UIkit.notification({
        message:msg,
        status:style,
        pos:dir
      });
   }

   function ajaxPost(url,formId) {
     return new Promise(function (resolve,reject) {
           $.ajax({
               url:url,
               method:'POST',
               data:$(formId).serialize(),
               success:function (d) {
                   if(d.status != 200){
                       reject(d);
                   }else {
                       resolve(d);
                   }
               },
               error:function (e) {
                   reject(e);
               }
           });
       });
   }

