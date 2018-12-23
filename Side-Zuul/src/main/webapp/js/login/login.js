/**
 * 登录控制js
 * @returns
 */
$(function (){
	
	$(window).load(function(){
		if(loginMsg != null){
			reset();
	        alertify.error(loginMsg);
	        return false;
		}
	});
});

reset = function () {
    $("toggleCSS").href = "css/alertify.core.css";
    alertify.set({
      labels: {
        ok: "OK",
        cancel: "Cancel"
      },
      delay: 5000,
      buttonReverse: false,
      buttonFocus: "ok"
    });
 };