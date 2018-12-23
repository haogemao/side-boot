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
				isParent : 0
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
						}
					}
				}).catch(response => {
					alertify.error("查询发送异常，请联系管理员");
				});
				
			},
			childSelect : function(){
				this.menuObject.key = app.child_search;
				this.menuObject.isParent = 1;
				axios({
					method : 'get',
					url : ctxPath + 'menu/search',
					params : {key : this.menuObject.key,
							  isParent : this.menuObject.isParent,
							  parentId : app.parentId
					}
				}).then(function(response){
					if(response.data.success){
						if(response.data.data != null){
							app.childList = new Array();
							app.childList = eval(response.data.data);
						}
					}
				}).catch(response => {
					alertify.error("查询发送异常，请联系管理员");
				});
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
						}
					}
				}).catch(response => {
					alertify.error("查询发送异常，请联系管理员");
				});
			},
			childChoose : function(menuId){
				app.childId = menuId;
			},
			addMenu : function(){
				$("#first_control").hide();
				$("#last_control").show();
				$("#parentModal").modal('show');
				app.menuObject.isParent = 0;
				app.childId = '';
				app.menuObject.parentMenu = '';
				app.parentId = '';
			},
			editMenu : function(){
				if(app.parentId == undefined || app.parentId == ''){
					alertify.alert("请选择需要编辑的记录");
					return;
				} else {
					// update init form data
					for(var i = 0; i < app.menuList.length; i ++){
						var menu = app.menuList[i];
						if(menu.menuId == app.parentId){
							app.menuObject = copy(menu);
							break;
						}
					}

					$("#first_control").hide();
					$("#last_control").show();
					$("#parentModal").modal('show');
					$("#exemple").attr("data-icon", app.menuObject.icon)
					app.menuObject.isParent = 0;
					app.childId = '';
					app.menuObject.parentMenu = app.parentId;
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
				if(app.childId == undefined || app.childId == ''){
					alertify.alert("请选择需要编辑的记录");
					return;
				} else {

					for(var i = 0; i < app.childList.length; i ++){
						var menu = app.childList[i];
						if(menu.menuId == app.childId){
							app.menuObject = copy(menu);
							break;
						}
					}

					$("#parentModal").modal('show');
					$("#first_control").show();
					app.parentMenu = app.parentId;
					$("#last_control").hide();
					$("#parentMenu").val(app.menuName);
					app.menuObject.isParent = 1;
				}
			},
			delChild : function(){
				if(app.childId == undefined || app.childId == ''){
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
					url : ctxPath + 'menu/editMenuService',
					params : this.menuObject
				}).then(function(response){
					if(response.data.success){
						if(response.data.data != null){
							if(app.parentId != undefined && app.parentId != '' && (app.childId != undefined && app.childId != '')){//二级菜单修改
								app.childList = new Array();
								app.childList = eval(response.data.data);
								$("#parentModal").modal('hide');
							} else if(app.parentId != undefined && app.parentId != '' && (app.childId == undefined || app.childId == '')){//二级菜单新增
								app.childList = new Array();
								app.childList = eval(response.data.data);
								$("#parentModal").modal('hide');
							}else { //父级菜单新增修改
								app.menuList = new Array();
								app.menuList = eval(response.data.data);
								$("#parentModal").modal('hide');
							}
							
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
		app._data.parentId = "",
		app._data.childId = ""
        $("#parentModal").modal('show');
	});
	
	//对象复制
	function copy(obj){
		var newobj = {};
		for ( var attr in obj) {
			newobj[attr] = obj[attr];
		}
		return newobj;
	}

});