$(function(){
	
	var userInfo = new Vue({
		el : '#userBtn',
		data : {
			user_info : {
				userCode : "",
				userName : "",
				userId : ""
			}
		},
		created : function(){
			axios({
				method : 'get',
				url : '/api/side/getUserInfo'
			}).then(function(response){
				if(response.sideUser != null){
					if(response.data.data != null){
						app.menuList = new Array();
						app.menuList = eval(response.data.data);
					}
				}
			}).catch(response => {
				alertify.error("查询发送异常，请联系管理员");
			});
		}
	})
	
//	//默认加载第一个菜单中第一个子菜单地址
//	var url = ctxPath + $(".heading").attr("data-path");
//	var iframe = $("#content_frame");
//	if(url != null && url.length > 0){
//		iframe.attr("src",url);
//	}
//	
//	//顶部菜单栏选择事件
//	$(".top-nav li a").each(function(){
//		$(this).click(function(){
//			$(".selected").removeAttr("class");
//			if($(this).attr("class") == "selected"){
//				return;
//			} else {
//				var menuId = $(this).attr("data-menuId");
//				$(this).attr("class","selected");
//			}
//		})
//	});
//	
//	//子菜单选择事件
//	$(".sub-nav li a").each(function(){
//		$(this).click(function(){
//			$(".heading").removeAttr("class");
//			if($(this).attr("class") == "heading"){
//				return;
//			} else {
//				$(this).attr("class", "heading");
//				var rqeust_url = ctxPath + $(this).attr("data-path");
//				$("#content_frame").attr("src", rqeust_url);
//			}
//		})
//	});
	
});


