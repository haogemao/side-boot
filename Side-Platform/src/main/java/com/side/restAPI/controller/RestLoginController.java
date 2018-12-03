/**
 * 
 */
package com.side.restAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.side.admin.IService.ISideAccountService;
import com.side.admin.IService.ISideAdminUserService;
import com.side.admin.pojo.AdminUser;
import com.side.authorization.IService.IAuthorizationService;
import com.side.authorization.IService.IUserRoleService;
import com.side.authorization.pojo.SideUserRole;
import com.side.basic.common.utils.DetachedCriteriaTS;

import jodd.util.StringUtil;


/**
 * @author gmc
 *
 */

@RestController
@RequestMapping("/restLoginAPI")
public class RestLoginController {
	

	@Qualifier("sideAccountService")
	@Autowired
	private ISideAccountService sideAccountService;
	
	@Qualifier("sideAdminUserService")
	@Autowired
	private ISideAdminUserService sideAdminUserService;
	
	@Qualifier("userRoleService")
	@Autowired
	private IUserRoleService userRoleService;
	
	@Qualifier("authorizationService")
	@Autowired
	private IAuthorizationService authorizationService;
	
	@RequestMapping(value="/restLogin", method=RequestMethod.GET)
	public String restLogin(String username, String passwrod) {
		
		if(username == null || "".equals(username) || passwrod == null && "".equals(passwrod)) {
			return "请输入用户名，密码！";
		}
		
		AdminUser adminUser = sideAdminUserService.findAdminUserByAdminCode(username);
		if(adminUser != null) {
			return "登录成功！";
		}
		return null;
	}

}
