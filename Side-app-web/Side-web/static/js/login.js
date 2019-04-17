import axios from '../router/http'
import * as types from './store/types'

export default{
	data : {
		usercode:"",
		pwd:""
	},
	methods : {
		dologin : function(){
			axios({
				method : 'post',
				param : {
					username : this.usercode,
					password : this.pwd
				},
				url : '/api/auth-center/oauth/token'
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
	}
}
