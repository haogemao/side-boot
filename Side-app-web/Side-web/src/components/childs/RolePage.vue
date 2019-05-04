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
										<tr v-on:click="roleSelect(role.roleId)" v-for="role in roleList">
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
									<input type="text" name="roleCode" id="roleCode" v-model="roleObject.roleCode">
								</div>
							</div>
							<div class="control-group warning">
								<label class="control-label">角色名称</label>
								<div class="controls">
									<input type="text" name="roleName" id="roleName" v-model="roleObject.roleName">
								</div>
							</div>
							<div class="control-group warning">
								<label class="control-label">角色状态</label>
								<div class="controls">
									<select name="roleStatus" id="roleStatus" v-model="roleObject.roleStatus">
										<option value="0">正常</option>
										<option value="1">失效</option>
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
				
			},
			roleSelect : function(roleId){
				
			},
			addRole : function(){
				
			},
			editRole : function(){
				
			},
			delRole : function(){
				
			},
			roleSave : function(){
				
			}
		}
	}
</script>

<style>
</style>