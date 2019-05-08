<template>
	<div id="contianer" class="dashboard-wrapper">
		<div class="left-sidebar">
			<div class="row-fluid">
				<div class="span5">
					<div class="widget">
						<div class="widget-header">
							<div class="title"><span>角色列表</span></div>
						</div>
						<div class="widget-body">
							<div id="dt_example" class="example_alt_pagination">
								<div class="dataTables_wrapper" id="data-table_wrapper" role="gird">
									<div class="dataTables_length">
										<button type="button" class="btn btn-small btn-success bottom-margin" v-on:click="addRole">新增</button>
										<button type="button" class="btn btn-small btn-warning2 bottom-margin" v-on:click="editRole">修改</button>
										<button type="button" class="btn btn-small btn-danger bottom-margin" v-on:click="delRole">删除</button>
									</div>
									<!-- 查询条件 -->
									<div class="dataTables_filter" id="data-table_filter">
										<label>
											查询:
											<input type="text" v-model="searchKey" v-on:keyup="roleSearch" aria-controls="data-table">
										</label>
									</div>
									<!-- 表格主题 -->
									<table style="margin-bottom: 20px;" class="table table-condensed table-striped table-bordered table-hover no-margin">
									<thead>
										<tr>
											<th style="display:none;">
												角色ID
											</th>
											<th>角色编码</th>
											<th>角色名称</th>
										</tr>
									</thead>
									<tbody>
										<tr v-on:click="roleSelect(index)" v-for="(role, index) in roleList">
											<td style="display:none;">
												<input type="hidden" ref="pid" v-model="role.roleId">
											</td>
											<td style="width: 120px;">
												{{role.roleCode}}
											</td>
											<td>
												{{role.roleName}}
											</td>
										</tr>
									</tbody>
									</table>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 新增编辑弹出框 -->
			<div class="modal hide fade" id="roleModal" tabindex="-1" style="display: none;" role="dialog" aria-labelledby="roleModalLabel">
				<div class="widget no-margin" role="document">
					<div class="widget-header">
						<div class="title">角色编辑</div>
						<div class="tools">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						</div>
					</div>
					<div class="widget-body">
						<form id="editForm" class="form-horizontal no-margin" action="">
							<div class="control-group warning">
								<label class="control-label">角色编码</label>
								<div class="controls">
									<input type="text" name="roleCode" id="roleCode" v-model="myRole.roleCode">
								</div>
							</div>
							<div class="control-group warning">
								<label class="control-label">角色名称</label>
								<div class="controls">
									<input type="text" name="roleName" id="roleName" v-model="myRole.roleName">
								</div>
							</div>
							<div class="control-group warning">
								<label class="control-label">角色状态</label>
								<div class="controls">
									<select name="roleStatus" id="roleStatus" v-model="myRole.roleStatus">
										<option value="1">正常</option>
										<option value="0">失效</option>
									</select>
								</div>
							</div>
							<hr>
							<div class="next-prev-btn-container pull-right">
								<button type="button" class="button" v-on:click="roleSave">提交</button>
								<button type="button" class="button" data-dismiss="modal">取消</button>
							</div>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</template>

<script>
	export default{
		name: "rolePage",
		data : function(){
			return{
				roleObject : {
					roleId : null,
					roleCode : null,
					roleName : null,
					roleStatus : null,
					key : null
				},
				myRole : {
					
				},
				roleList:[],
				searchKey : null
			}
		},
		created : function(){
			let _this = this;
			this.axios({
				method : 'get',
				url : '/side/role/list',
				params : _this.roleObject
			}).then(function(response){
				if(response.data.retCode == "0000"){
					if(response.data.record != null){
						_this.$data.roleList = response.data.record;
					}
				}
			}).catch(response => {
				this.$alertify.error("查询发送异常，请联系管理员");
			});
		},
		methods : {
			roleSearch : function(){
				let _this = this;
				_this.roleObject.key = this.$data.searchKey;
				this.axios({
					method : 'get',
					url : '/side/role/list',
					params : _this.roleObject
				}).then(function(response){
					if(response.data.retCode == "0000"){
						if(response.data.record != null){
							_this.$data.roleList = response.data.record;
						}
					}
				}).catch(response => {
					this.$alertify.error("查询发送异常，请联系管理员");
				});
			},
			roleSelect : function(arrayIndex){
				this.$data.myRole = this.$data.roleList[arrayIndex];
			},
			addRole : function(){
				this.$data.myRole = {};
				$("#roleModal").modal("show");
			},
			editRole : function(){
				if(this.$data.myRole.roleId == null || this.$data.myRole.roleId == undefined){
					this.$alertify.error("请选择需要编辑的角色");
					return;
				}
				$("#roleModal").modal("show");
			},
			delRole : function(){
				let _this = this;
				if(this.$data.myRole.roleId == null || this.$data.myRole.roleId == undefined){
					this.$alertify.error("请选择需要删除的角色");
					return;
				} else {
					this.$alertify.confirmWithTitle(
						'提示!',
						'你确定删除该角色吗？',
						() => {
							$(".alertify").css("display", "none");
							_this.roleObject.roleId = this.$data.myRole.roleId;
							//发送请求
							this.axios({
								method : 'get',
								url : "/side/role/delRole",
								params : _this.roleObject
							}).then(response => {
								if(response.data.retCode == "0000"){
									if(response.data.record != null){
										_this.$data.roleList = response.data.record;
									}
								}
							}).catch(response => {
								if (response.data.retMsg != null){
									this.$alertify.error(response.data.retMsg);
								} else {
									this.$alertify.error("执行时发生异常，请联系管理员");
								}
							});
						},
						null
					);
				}
			},
			roleSave : function(){
				let _this = this;
				_this.roleObject.roleId = this.$data.myRole.roleId;
				_this.roleObject.roleCode = this.$data.myRole.roleCode;
				_this.roleObject.roleName = this.$data.myRole.roleName;
				_this.roleObject.roleStatus = this.$data.myRole.roleStatus;
//				_this.roleObject = copy(this.$data.myRole);
				_this.axios({
					method : "POST",
					url : "/side/role/saveRole",
					data : _this.roleObject
				}).then(response => {
					if(response.data.retCode == "0000"){
						$("#roleModal").modal("hide");
						if(response.data.record != null && response.data.record.length > 0){
							_this.$data.roleList = response.data.record;
						}
						this.$alertify.success(response.data.retMsg);
					}
				}).catch(response => {
					if (response.data.retMsg != null){
						this.$alertify.error(response.data.retMsg);
					} else {
						this.$alertify.error("执行时发生异常，请联系管理员");
					}
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