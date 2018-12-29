/**
 * 登录控制js
 * @returns
 */
$(function (){
	
	var myData = {};
	
//	$(window).load(function(){
//		if(loginMsg != null){
//			reset();
//	        alertify.error(loginMsg);
//	        return false;
//		}
//	});
	
	//提交方法
	$("#submit").click(function(){
		
		var user_code = $("#userCode").val();
		var password = $("#password").val();
		
		if(!(user_code != undefined && user_code.length > 0 && password != undefined && password.length > 0)){
			alertify.error("请输入用户名和密码!");
			return;
		} else {
			
			myData.username = user_code;
			myData.password = password;
			
			$.ajax({
				url : "/api/auth-center/oauth/token",
				type : "POST",
				data : myData,
				dataType : "json",
				async: false,
			    contentType: 'application/x-www-form-urlencoded',
				success : function(result){
					var access_token  = result.access_token;
					if(access_token != null){
						window.sessionStorage.setItem('access_token', access_token);
						window.location.href="/pages/index/main.html";
					}
					alertify.error("登录成功");
				},
				error : function(xhr, status){
					alertify.error("登录失败");
				}
			})
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