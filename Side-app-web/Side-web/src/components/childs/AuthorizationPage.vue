<template>
	<div id="contianer" class="dashboard-wrapper">
		<div class="left-sidebar">
			<div class="row-fluid hidden-phone">
              <div class="span12">
                <div class="widget">
                  <div class="widget-header">
                    <div class="title">
                      角色权限
                      <span class="mini-title">
                        为角色赋操作权限<!--<a id="leftTabs">d</a>-->
                      </span>
                    </div>
                  </div>
                  <div class="widget-body">
                    
                    <div id="tabsleft" class="tabbable tabs-left">
                      <ul class="nav nav-tabs">
                        <li v-for="role in roles">
                          <a href="#tabsleft-tab1" data-toggle="tab" v-on:click="selectRole(role.roleId)">
                            {{role.roleName}}
                          </a>
                        </li>
                      </ul>
                      <div class="tab-content">
                        <div class="tab-pane" id="tabsleft-tab1">
                         <!-- 权限列表 begin-->
                          <div id="accordion1" class="accordion no-margin">
		                      <div class="accordion-group" v-for="(menu, index) in menus">
		                        <div class="accordion-heading">
		                          <a :href="'#'+menu.menuId" data-parent="#accordion1" data-toggle="collapse" class="accordion-toggle" v-on:click="getAuthorization(menu.menuId)">
		                            {{menu.menuName}}
		                          </a>
		                        </div>
		                        <div class="accordion-body collapse" :id="menu.menuId" style="height: 0px;">
		                          <div class="accordion-inner">
	                          		<input :id="menu.menuId + '_0'" type="checkbox" v-model="operationList" v-on:click="chooseAuthorization(0)" style="width: 14px; height: 14px; margin: auto;" value="0"/>&nbsp;查询&nbsp;&nbsp;&nbsp;&nbsp;
	                           		<input :id="menu.menuId + '_1'" type="checkbox" v-model="operationList" v-on:click="chooseAuthorization(1)" style="width: 14px; height: 14px; margin: auto;" value="1"/>&nbsp;新增&nbsp;&nbsp;&nbsp;&nbsp;
	                           		<input :id="menu.menuId + '_2'" type="checkbox" v-model="operationList" v-on:click="chooseAuthorization(2)" style="width: 14px; height: 14px; margin: auto;" value="2"/>&nbsp;编辑&nbsp;&nbsp;&nbsp;&nbsp;
	                           		<input :id="menu.menuId + '_3'" type="checkbox" v-model="operationList" v-on:click="chooseAuthorization(3)" style="width: 14px; height: 14px; margin: auto;" value="3"/>&nbsp;删除&nbsp;&nbsp;&nbsp;&nbsp;
		                           	<input :id="menu.menuId + '_4'" type="checkbox" v-model="operationList" v-on:click="chooseAuthorization(4)" style="width: 14px; height: 14px; margin: auto;" value="4"/>&nbsp;导入&nbsp;&nbsp;&nbsp;&nbsp;
		                           	<input :id="menu.menuId + '_5'" type="checkbox" v-model="operationList" v-on:click="chooseAuthorization(5)" style="width: 14px; height: 14px; margin: auto;" value="5"/>&nbsp;导出&nbsp;&nbsp;&nbsp;&nbsp;
		                          </div>
		                        </div>
		                      </div>
	                    		</div>
                         <!-- 权限列表 end-->
                        </div>
                        <ul class="pager wizard no-margin">
                          <li class="next finish">
                            <a href="#" v-on:click="save()">
                              Finish
                            </a>
                          </li>
                        </ul>
                      </div>
                      
                    </div>
                    
                  </div>
                </div>
              </div>
            </div>
		</div>
	</div>
</template>

<script>
	export default{
		name: "authorizationPage",
		data : function(){
			return{
				authorizations : null,
				menus : [],
				roles : [],
				authorization : {
					authorizationId : null,
					roleId : null,
					menuId : null,
					operationList : ""
				},
				operationList: []
			}
		},
		created : function(){
			let _this = this;
			this.authorizations = new Array();
			//获取全部菜单
			this.axios({
				method: 'get',
				url: '/side/menu/search',
				params : { isParent : '1', menuType: '0' }
			}).then(function(response){
				if(response.data.success){
					if(response.data.data != null){
						_this.$data.menus = JSON.parse(response.data.data);
					}
				}
			}).catch(function(response){
				_this.$this.$alertify.error("查询发送异常，请联系管理员");
			});
			
			//获取全部角色
			this.axios({
				method : 'get',
				url : '/side/role/list'
			}).then(function(response){
				if(response.data.retCode == "0000"){
					if(response.data.record != null){
						_this.$data.roles = response.data.record;
					}
				}
			}).catch(function(response){
				_this.$alertify.error("查询发送异常，请联系管理员");
			})
		},
		methods : {
			selectRole: function(roleId){
				if (roleId != null && roleId != undefined) {
					this.authorization.roleId = roleId;
				} else {
					this.$alertify.error("角色id不存在");
				}
			},
			
			getAuthorization: function(menuId){
				let _this = this;
				if (menuId != null && menuId != undefined && this.authorization.roleId != null && this.authorization.roleId != undefined) {
					this.authorization.menuId = menuId;
				} else {
					this.$alertify.error("请先选择角色与菜单");
					return;
				}
				this.axios({
					method: 'get',
					url: '/side/authorization/roleInfo',
					params: this.authorization
				}).then(response => {
					if(response.data.retCode == '0000'){
						_this.$data.operationList = [];
						_this.$data.authorization.operationList = [];
						_this.$data.authorization.authorizationId = null;
						if(response.data.record.length > 0){
							let record = response.data.record[0];
							_this.$data.authorization.authorizationId = record.authorizationId;
							_this.$data.authorization.operationList = record.operationList;
							if(record.operationList.indexOf(_this.store.state.operation.add) != -1){
								_this.$data.operationList[0] = _this.store.state.operation.add;
							} 
							if(record.operationList.indexOf(_this.store.state.operation.edit) != -1){
								_this.$data.operationList[1] = _this.store.state.operation.edit;
							}
							if(record.operationList.indexOf(_this.store.state.operation.del) != -1){
								_this.$data.operationList[2] = _this.store.state.operation.del;
							}
							if(record.operationList.indexOf(_this.store.state.operation.query) != -1){
								_this.$data.operationList[3] = _this.store.state.operation.query;
							}
							if(record.operationList.indexOf(_this.store.state.operation.imp) != -1){
								_this.$data.operationList[4] = _this.store.state.operation.imp;
							}
							if(record.operationList.indexOf(_this.store.state.operation.exp) != -1){
								_this.$data.operationList[5] = _this.store.state.operation.exp;
							}
						}
					}
				}).catch(response => {
					_this.$alertify.error(response.data.retMsg);
				})
			},
			
			chooseAuthorization: function(val){
				let valStr = val+"";
				let isHas = true;
				if(this.authorization.operationList.indexOf(valStr) == -1){
					this.authorization.operationList += valStr;
				} else {
					this.authorization.operationList = this.authorization.operationList.replace(valStr, "");
				}
				let newuthorization = copy(this.authorization);
				if(this.authorizations != null && this.authorizations.length > 0){
					for (let i = 0; i < this.authorizations.length; i++) {
						if(this.authorizations[i].menuId == newuthorization.menuId && this.authorizations[i].roleId == newuthorization.roleId){
							this.authorizations[i].operationList = newuthorization.operationList;
							isHas = true;
							break;
						} else {
							isHas = false;
							continue;
						}
					}
					if(!isHas){
						this.authorizations.push(newuthorization);
					}
				} else {
					this.authorizations.push(newuthorization);
				}
			},
			
			save: function(){
				let _this = this;
				this.axios({
					method : 'POST',
					url : '/side/authorization/saveAuthorization',
					data : this.authorizations //post请求使用data对象
					//params : this.authorizations (get请求使用params参数)
//					transformRequest : [function(data){
//						data = JSON.stringify(data);
//						return data;
//					}]
				}).then(function(response){
					if(response.data.retCode == "0000"){
						_this.$alertify.success(response.data.retMsg);
					}
				}).catch(function(response){
					_this.$alertify.error("系统异常，请联系管理员");
				})
			}
			
		}
	}
	
	//对象复制
	function copy(obj){
		var newobj = {};
		for ( var attr in obj) {
			newobj[attr] = obj[attr];
		}
		return newobj;
	}
</script>

<style>
</style>