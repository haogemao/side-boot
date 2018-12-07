/**
 * 登录控制js
 * @returns
 */
$(function (){
	
	var client_id="client";
	var secret="secret";
	
	var myData = {};
	
	$(window).load(function(){
		if(loginMsg != null){
			reset();
	        alertify.error(loginMsg);
	        return false;
		}
	});
	
	//提交方法
	$("#submit").click(function(){
		
		var user_code = $("#userCode").val();
		var password = $("#password").val();
		var grant_type = $("#grant_type").val();
		
		if(!(user_code != undefined && user_code.length > 0 && password != undefined && password.length > 0)){
			alertify.error("请输入用户名和密码!");
			return;
		} else {
			
			myData.username = user_code;
			myData.password = password;
			myData.grant_type = grant_type;
			myData.client_id = client_id;
			myData.client_secret = secret;
			
			$.ajax({
				url : ctxPath + "oauth/token",
				type : "POST",
				data : myData,
				dataType : "json",
				async: false,
			    contentType: 'application/x-www-form-urlencoded',
				success : function(result){
					var access_token  = result.access_token;
					if(access_token != null){
						window.location.href="http://localhost:8801/side/index?access_token="+access_token;
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