<template>
<div>
	<header>
      <a href="#" class="logo">
        <img src="../assets/logo/logo.png" alt="Logo"/>
      </a>
      <div class="btn-group">
        <button id="userBtn" class="btn btn-primary" v-model="user_info" >{{user_info.userName}}</button>
        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle">
          <span class="caret">
          </span>
        </button>
        <ul class="dropdown-menu pull-right">
          <li>
            <a href="#">
              编辑个人信息
            </a>
          </li>
          <li>
            <a href="#">
              账号设置
            </a>
          </li>
          <li>
            <a href="#">
              Logout
            </a>
          </li>
        </ul>
      </div>
      <ul class="mini-nav">
        <li>
          <a href="#">
            <div class="fs1" aria-hidden="true" :data-icon="icon1"></div>
           
          </a>
        </li>
        <li>
          <a href="#">
            <div class="fs1" aria-hidden="true" :data-icon="icon2"></div>

          </a>
        </li>
      </ul>
    </header>
    <div class="container-fluid">
    	<div class="dashboard-container">
    		<div class="top-nav">
    			<ul>
    				<li v-for="(parentMenu, index) in parentMenuArray">
    					<a href="#" v-if="index==0" class="selected">
    						<div class="fs1" aria-hidden="true" :data-icon="parentMenu.icon"></div>
    						{{parentMenu.menuName}}
    					</a>
    					<a href="#" v-else>
    						<div class="fs1" aria-hidden="true" :data-icon="parentMenu.icon"></div>
    						{{parentMenu.menuName}}
    					</a>
    				</li>
    			</ul>
    			<div class="clearfix"></div>
    		</div>
    		<div class="sub-nav">
    			<ul>
    				<li v-for="(child, index) in childMenuArray">
    					<a href="#" v-if="index==0" class="heading" >{{child.menuName}}</a>
    					<a href="#" v-else>{{child.menuName}}</a>
    				</li>
    			</ul>
    		</div>
    		<div>
    			<router-view></router-view>
    		</div>
    	</div>
    </div>
    
    <footer>
      <p>
        &copy; gmc 2018
      </p>
    </footer>
</div>
</template>

<script>
	export default{
		name : 'mainpage',
		data() {
			return {
				user_info : null,
				parentMenuArray : null,
				childMenuArray : null,
				icon1 : '\ue040',
				icon2 : '\ue04c'
			}
		},
		created : function(){
			let _this = this;
			this.axios({
				method : 'get',
				url : '/side/dologin'
			}).then(function(response) {
				if(response.data.user_info != null){
					//把用户信息赋值到user_info对象中
					_this.$data.user_info = response.data.user_info;
				}
				if(response.data.parents != null && response.data.parents.length > 0){
					_this.$data.parentMenuArray = response.data.parents;
				}
				if(response.data.firstChilds != null && response.data.firstChilds.length > 0){
					_this.$data.childMenuArray = response.data.firstChilds;
				}
				_this.router.push({
					path: '/main/menus'
				});
			}).catch(response => {
				this.$alertify.error("查询发送异常，请联系管理员");
			});
		},
		methods : {
				
		}
	}
</script>

<style>
</style>