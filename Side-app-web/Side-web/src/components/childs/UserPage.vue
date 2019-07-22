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
							      <tr class="gradeX warning">
							        <td>
							          00001
							        </td>
							        <td>
							          例子用户
							        </td>
							        <td>
							          系统管理员
							        </td>
							        <td class="hidden-phone">
							          正常
							        </td>
							        <td class="hidden-phone">
							          系统管理员
							        </td>
							        <td class="hidden-phone">
							         2019-07-21
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
	
</script>

<style>
</style>