var token=null;
$.ajaxSetup({
//拦截所有ajax请求
    beforeSend: function(xhr) {
    
    	var token_url = "/oauth/token";
    	var authoriz_url = "/oauth/authorize";
    	var client_id="client";
    	var secret="secret";
    	var grant_type = "password";
    	
        var params = arguments[1].data;
        var url = arguments[1].url;
        var data = '';
        var datas = new Array();
         
         if(url.search(token_url) > 0){
        	 arguments[1].data = params
        	 					+ "&client_id=" + client_id 
        	 					+ "&client_secret=" + secret 
        	 					+ "&grant_type=" + grant_type;
        	 
         } else {
        	 var token = window.sessionStorage.getItem('access_token');
        	 xhr.setRequestHeader("Authorization", "Bearer " + token);
        	 
         }
     }
});
