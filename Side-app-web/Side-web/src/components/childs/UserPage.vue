<template>
	<div id="contianer" class="dashboard-wrapper">
		<div class="left-sidebar">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget">
						<div class="widget-header">
							<div class="title"><span>用户列表</span></div>
						</div>
						<div class="widget-body">
							<div id="userGrid" class="example_alt_pagination">
							  <table class="table table-condensed table-striped table-hover table-bordered pull-left" id="data-table">
							    <thead>
							      <tr>
							        <th style="width:17%">
							          用户编码
							        </th>
							        <th style="width:20%">
							          用户名称
							        </th>
							        <th style="width:16%">
							          用户角色
							        </th>
							        <th style="width:16%">
							          用户状态
							        </th>
							        <th style="width:16%">
							          创建人
							        </th>
							        <th style="width:16%">
							          创建日期
							        </th>
							      </tr>
							    </thead>
							    <tbody>
							      <tr class="gradeX warning" v-for="user in userList">
							      	<td style="display: none;">
										<input type="hidden" ref="uid" v-model="user.userId">
									</td>
							        <td>
							          {{user.userCode}}
							        </td>
							        <td>
							          {{user.userName}}
							        </td>
							        <td>
							          {{user.roleName}}
							        </td>
							        <td>
							          {{user.userStatus}}
							        </td>
							        <td class="hidden-phone">
							          {{user.createBy}}
							        </td>
							        <td class="hidden-phone">
							          {{user.createDate}}
							        </td>
							      </tr>
							    </tbody>
							  </table>
							  <div class="clearfix">
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
		name : "userPage",
		data : function(){
			return {
				userList : [],
				searchText : "",
				userInfo : {
					userId : "",
					userName : "",
					userCode : "", 
					userStatus : "",
					searchKey : ""
				}
			}
		},
		create : function(){
			let _this = this;
			this.axios({
				method : "get",
				url : '/side/users/getUserList',
				params : {key : _this.userInfo.searchKey}
			}).then(response => {
				_this.$data.userList = JSON.parse(response.data.data);
			}).catch(response => {
				
			})
		},
		method : {
			
		}
	}
</script>

<style>
</style>