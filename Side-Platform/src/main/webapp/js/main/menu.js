$(function(){
	var app = new Vue({
		
		el : '#contianer',
		data : {
			parent_search : "",
			child_search : "",
			parentId : "", //用于获取当前选择的一级菜单id
			childId : "",  //用户获取当前选择的二级菜单id
			menuName : "",
			icon : "",
			menuObject : {
				menuId : "",
				menuCode : "",
				menuName : "",
				menuPath : "",
				menuSort : "",
				menuType : "",
				icon : "",
				parentMenu : "",
				isParent : 0,
				key : ""
			},
			menu : {
				menuId : "",
				menuCode : "",
				menuName : "",
				menuPath : "",
				menuSort : "",
				menuType : "",
				icon : "",
				parentMenu : "",
				isParent : ""
			},
			menuList : [],
			childList : [],
		},
		created : function() {
			this.menuObject.isParent = 0;
			axios({
				method : 'get',
				url : ctxPath + 'menu/search',
				params : {key : this.menuObject.key,
						isParent : this.menuObject.isParent
				}
			}).then(function(response){
				if(response.data.success){
					if(response.data.data != null){
						app.menuList = new Array();
						app.menuList = eval(response.data.data);
						// var datas = eval(response.data.data);
						// for(var i = 0; i < datas.length; i++){
						// 	app.menuList.push(datas[i]);
						// }
					}
				}
			}).catch(response => {
				alertify.error("查询发送异常，请联系管理员");
			});
		},
		methods : {
			parentSelect : function(){
				this.menuObject.key = app.parent_search;
				this.menuObject.isParent = 0;
				axios({
					method : 'get',
					url : ctxPath + 'menu/search',
					params : {key : this.menuObject.key,
							isParent : this.menuObject.isParent
					}
				}).then(function(response){
					if(response.data.success){
						if(response.data.data != null){
							app.menuList = new Array();
							app.menuList = eval(response.data.data);
							// var datas = eval(response.data.data);
							// for(var i = 0; i < datas.length; i++){
							// 	app.menuList.push(datas[i]);
							// }
						}
					}
				}).catch(response => {
					alertify.error("查询发送异常，请联系管理员");
				});
				
			},
			childSelect : function(){
				alertify.error("当前输入框的值:" + this.child_search);
			},
			select : function(menuId, event){//父级菜单选择触发事件				
				app.parentId = menuId;
				app.menuName = event.currentTarget.lastChild.innerText;
				axios({
					method : 'get',
					url : ctxPath + 'menu/search',
					params : {parentId : menuId}
				}).then(function(response){
					if(response.data.success){
						if(response.data.data != null){
							app.childList = new Array();
							app.childList = eval(response.data.data);
						// var datas = eval(response.data.data);
						// for(var i = 0; i < datas.length; i++){
						// 	app.menuList.push(datas[i]);
						// }
						}
					}
				}).catch(response => {
					alertify.error("查询发送异常，请联系管理员");
				});
			},
			childSelect : function(menuId){
				app.childId = menuId;
			},
			addMenu : function(){
				$("#first_control").hide();
				$("#last_control").show();
				$("#parentModal").modal('show');
				app.menuObject.isParent = 0;
				app.childId = '';
			},
			editMenu : function(){
				if(app.parentId == undefined || app.parentId == ''){
					alertify.alert("请选择需要编辑的记录");
					return;
				} else {
					$("#first_control").hide();
					$("#last_control").show();
					$("#parentModal").modal('show');
					app.menuObject.isParent = 0;
					app.childId = '';
				}
			},
			delMenu : function(){
				if(app.parentId == undefined || app.parentId == ''){
					alertify.alert("请选择需要删除的记录");
					return;
				} else {
					
				}
			},
			addChild : function(){
				if(app.parentId != null && app.parentId != undefined){
					$("#first_control").show();
					app.parentMenu = app.parentId;
					$("#last_control").hide();
					$("#parentModal").modal('show');
					$("#parentMenu").val(app.menuName)
					app.menuObject.isParent = 1;
					app.childId = '';
				} else {
					alertify.alert("未选定需要添加二级菜单的一级菜单");
					return;
				}
			},
			editChild : function(){
				if(app.parentId == undefined || app.parentId == ''){
					alertify.alert("请选择需要编辑的记录");
					return;
				} else {
					$("#parentModal").modal('show');
					$("#first_control").show();
					app.parentMenu = app.parentId;
					$("#last_control").hide();
					$("#parentMenu").val(app.menuName);
					app.menuObject.isParent = 1;
				}
			},
			delChild : function(){
				if(app.parentId == undefined || app.parentId == ''){
					alertify.alert("请选择需要删除的记录");
					return;
				} else {
					
				}
			},
			submit :function(){
				if(app.parentId != undefined && app.parentId != '' && (app.childId != undefined && app.childId != '')){
					this.menuObject.parentMenu = app.parentId;
					this.menuObject.menuId = app.childId;
				} else if(app.parentId != undefined && app.parentId != '' && (app.childId == undefined || app.childId == '')){
					this.menuObject.parentMenu = app.parentId;
				}else {
					this.menuObject.menuId = app.parentId;
				}
				//发送请求
				axios({
					method : 'get',
					url : ctxPath + 'menu/addMenuService',
					params : this.menuObject
				}).then(function(response){
					if(response.data.success){
						if(response.data.data != null){
							app.childList = new Array();
							app.childList = eval(response.data.data);
							$("#parentModal").modal('hide');
						}
					}
				}).catch(response => {
					alertify.error("查询发送异常，请联系管理员");
				});
				
			}
		}
	});

	//选择图标
	$("#icons li span").each(function(){
		$(this).click(function(){
			app._data.menuObject.icon = $(this).attr("data-icon");
			$("#menuIcon").val($(this).attr("data-icon"));
			$("#exemple").attr("data-icon", $(this).attr("data-icon"));
			$("#iconModal").modal('hide');
		});
	});
	
	$('#parentModal').on('hide.bs.modal',function() {
        $("#editForm").get(0).reset();
        app._data.menuObject.menuId = "",
        app._data.menuObject.menuCode = "",
        app._data.menuObject.menuName = "",
        app._data.menuObject.menuPath = "",
        app._data.menuObject.menuSort = "",
        app._data.menuObject.menuType = "",
        app._data.menuObject.icon = "",
        app._data.menuObject.parentMenu = "",
        app._data.menuObject.isParent = 0,
        app._data.menuObject.key = null;
        $("#parentModal").modal('show');
    });

});