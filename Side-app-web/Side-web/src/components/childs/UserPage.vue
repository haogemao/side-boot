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
							<div id="dt_example" class="example_alt_pagination">
		                      <div id="data-table_wrapper" class="dataTables_wrapper" role="grid">
			                      <div id="data-table_length" class="dataTables_length">
			                      	<button type="button" class="btn btn-small btn-success bottom-margin" v-on:click="openUserEdit('add')">新增</button>
									<button type="button" class="btn btn-small btn-warning2 bottom-margin" v-on:click="openUserEdit('edit')">修改</button>
									<button type="button" class="btn btn-small btn-danger bottom-margin" v-on:click="">删除</button>
			                      </div>
			                      <div class="dataTables_filter" id="data-table_filter">
			                      	<label>Search: <input type="text" v-model="userInfo.searchKey" v-on:keyup="searchUser" aria-controls="data-table"></label>
			                      </div>
			                      <table class="table table-condensed table-striped table-hover table-bordered pull-left" id="data-table" aria-describedby="data-table_info">
										    <thead>
										      <tr>
										        <th style="width:17%">
										          用户编码
										        </th>
										        <th style="width:20%">
										          用户名称
										        </th>
										        <th style="width:16%; display: none;">
										          角色id
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
										      <tr class="gradeA even" v-for="user in userList">
										      	<td style="display: none;">
													<input type="hidden" ref="uid" v-model="user.userId">
												</td>
										        <td>
										          {{user.userCode}}
										        </td>
										        <td>
										          {{user.userName}}
										        </td>
										        <td style="display: none;">
										          {{user.roleId}}
										        </td>
										        <td>
										          {{user.roleName}}
										        </td>
										        <td>
										          {{user.formatStatus}}
										        </td>
										        <td class="hidden-phone">
										          {{user.createBy}}
										        </td>
										        <td class="hidden-phone">
										          {{user.formatDate}}
										        </td>
										      </tr>
										    </tbody>
								 </table>
								 <div class="dataTables_info" id="data-table_info">{{pageInfo}}</div>
								<div class="dataTables_paginate paging_full_numbers" id="data-table_paginate">
									<a tabindex="0" class="first paginate_button paginate_button_disabled" id="data-table_first">First</a><a tabindex="0" class="previous paginate_button paginate_button_disabled" id="data-table_previous">Previous</a><span><a tabindex="0" class="paginate_active" v-for="index in pageCount">{{index}}</a></span><a tabindex="0" class="next paginate_button" id="data-table_next">Next</a><a tabindex="0" class="last paginate_button" id="data-table_last">Last</a>
								</div>
		                      </div>
		                      <div class="clearfix">
		                      </div>
		                    </div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 新增编辑弹出框 -->
			<div class="modal hide fade" id="userModal" tabindex="-1" style="display: none; " role="dialog" aria-labelledby="userModalLabel">
				<div class="widget no-margin" role="document">
					<div class="widget-header">
						<div class="title">用户编辑</div>
						<div class="tools">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						</div>
					</div>
					<div class="widget-body">
						<form class="form-horizontal no-margin">
	                      <div class="tab-content" id="myTabContent">
	                        <div id="step1" class="tab-pane fade active in">
	                          
	                          <div class="control-group">
	                            <label class="control-label" for="userCode">
	                              User Code
	                            </label>
	                            <div class="controls">
	                              <input type="text" id="userCode" placeholder="User Code">
	                            </div>
	                          </div>
	                          <div class="control-group">
	                            <label class="control-label" for="userName">
	                              User Name
	                            </label>
	                            <div class="controls">
	                              <input type="text" id="userName" placeholder="User Name">
	                            </div>
	                          </div>
	                          <div class="control-group">
	                            <label class="control-label" for="userStatus">
	                              User Status
	                            </label>
	                            <div class="controls">
	                            	 <select id="userStatus">
	                            	 	<option value="0">无效</option>
	                            	 	<option value="1">正常</option>
	                            	 	<option value="2">冻结</option>
	                            	 </select>
	                            </div>
	                          </div>
	                          
	                          <hr>
	                        </div>
	                        <div id="step2" class="tab-pane fade">
	                          <div class="control-group">
	                            <label class="control-label" for="accCode">
	                              Account Number
	                            </label>
	                            <div class="controls">
	                              <input type="text" id="accCode" placeholder="Account Number">
	                            </div>
	                          </div>
	                          <div class="control-group">
	                            <label class="control-label" for="accName">
	                              Account Name
	                            </label>
	                            <div class="controls">
	                              <input type="text" id="accName" placeholder="Account Name">
	                            </div>
	                          </div>
	                          <div class="control-group">
	                            <label class="control-label">
	                              Gender
	                            </label>
	                            <div class="controls">
	                              <label class="radio inline">
	                                <input type="radio" name="sex" value="M" checked>
	                                Male
	                              </label>
	                              <label class="radio inline">
	                                <input type="radio" name="sex" value="F">
	                                Female
	                              </label>
	                            </div>
	                          </div>
	                          
	                          <hr>
	                        </div>
	                        <div id="step3" class="tab-pane fade">
	                          <p>
	                            Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica.prehenderit butcher retro keffiyeh dreamcatcher synth. Reprehenderit butcher retro keffiyeh dreamcatcher synth terry richardsoAustin. 
	                          </p>
	                          <p>
	                            Reprehenderit butcher retro keffiyeh dreamcatcher synth terry richardsoAustin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica.
	                          </p>
	                          <label class="checkbox">
	                            <input type="checkbox" checked>
	                            Terms &amp; Conditions
	                          </label>
	                        </div>
	                        <div class="next-prev-btn-container pull-right" style="margin-right: 10px;">
	                          <a data-toggle="tab" href="#step1" class="button prev">
	                            First
	                          </a>
	                          <a data-toggle="tab" href="#step2" class="button">
	                            Next
	                          </a>
	                          <a data-toggle="tab" href="#step3" class="button next">
	                            Finish
	                          </a>
	                          <div class="clearfix">
	                          </div>
	                        </div>
	                      </div>
	                    </form>
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
				userInfo : {
					userId : "",
					userName : "",
					userCode : "", 
					userStatus : "",
					searchKey : ""
				},
				pageInfo : "Showing $1 to $2 of $3 entries",
				pageCount : 1,
				pageNumber : 1,
				pageSize : 10,
				hasNextPage : false,
				hasPreviousPage : false
			}
		},
		created : function(){
			let _this = this;
			this.axios({
				method : "get",
				url : '/side/users/getUserList',
				params : {dto : _this.userInfo, pageNumber : 1, pageSize : 10}
			}).then(response => {
				_this.$data.userList = response.data.pageMode.records;
				_this.$data.pageCount = response.data.pageMode.pageCount;
				_this.$data.pageInfo = _this.$data.pageInfo.replace("$1",response.data.pageMode.firstIndex)
														  .replace("$2",response.data.pageMode.lastIndex)
														  .replace("$3",response.data.pageMode.count);
				_this.$data.pageNumber = response.data.pageMode.pageNumber;
				_this.$data.pageSize = response.data.pageMode.pageSize;
			}).catch(response => {
				if (response.data != null && response.data != undefined){
					this.$alertify.error(response.data.retMsg);
				} else {
					this.$alertify.error("执行时发生异常，请联系管理员");
				}
			})
		},
		methods : {
			searchUser : function(){
				let _this = this;
				this.axios({
					method : "get",
					url : '/side/users/getUserList',
					params : {dto : _this.userInfo, pageNumber :  _this.pageNumber, pageSize :  _this.pageSize}
				}).then(response => {
					_this.$data.userList = response.data.pageMode.records;
					_this.$data.pageCount = response.data.pageMode.pageCount;
					_this.$data.pageInfo = _this.$data.pageInfo.replace("$1",response.data.pageMode.firstIndex)
															  .replace("$2",response.data.pageMode.lastIndex)
															  .replace("$3",response.data.pageMode.count);
					_this.$data.pageNumber = response.data.pageMode.pageNumber;
					_this.$data.pageSize = response.data.pageMode.pageSize;
				}).catch(response => {
					if (response.data != null && response.data != undefined){
						this.$alertify.error(response.data.retMsg);
					} else {
						this.$alertify.error("执行时发生异常，请联系管理员");
					}
				})
			},
			openUserEdit : function(btnType){
//				$("#userModal").empty();
				if(btnType == 'add'){
					$("#userModal").modal('show');
				} else {
					$("#userModal").modal('show');
				}
			},
			save : function(){
				
			},
			deleteUser : function(){
				
			},
			pageLoad : function(index){
				
			},
			toFirst : function(){
				
			},
			toLast : function(){
				
			},
			toNext : function(){
				
			},
			toPrevious : function(){
				
			}
		}
	}
</script>

<style>
</style>