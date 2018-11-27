/**
 * 
 */
package com.side.basic.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import com.side.admin.IService.ISideAccountService;
import com.side.admin.IService.ISideAdminUserService;
import com.side.admin.pojo.AdminUser;
import com.side.authorization.IService.IAuthorizationService;
import com.side.authorization.IService.IUserRoleService;
import com.side.authorization.pojo.SideAuthorization;

/**
 * @author gmc
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	@Qualifier("sideAdminUserService")
	private ISideAdminUserService sideAdminUserService;
	
	@Autowired
	@Qualifier("sideAccountService")
	private ISideAccountService sideAccountService;
	
	@Autowired
	@Qualifier("authorizationService")
	private IAuthorizationService authorizationService;
	
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;

	@RequestMapping(value="login", name="login", path="login", method=RequestMethod.GET)
	public String toLogin(ModelMap mode) {
		System.out.println("进入登录界面");
		return "index/login";
	}
	
	@RequestMapping(name="loginFail", path="loginFail", method=RequestMethod.GET)
	public String loginFail(ModelMap mode) {
		mode.put("msg", "登录失败");
		return "index/login";
	}
	
	@RequestMapping(value="dologin", name="dologin", path="dologin", method=RequestMethod.POST)
	public String doLogin(ModelMap mode, HttpSession session) {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<SideAuthorization> authorizations = null;
		List<SideAuthorization> parents = null;
		List<SideAuthorization> firstChilds = null;
		List<String> roles = new ArrayList<String>();
		AdminUser user = null;
		String userName = userDetails.getUsername();
		
		if(!userDetails.getAuthorities().isEmpty()) {
			Iterator iterator =  userDetails.getAuthorities().iterator();
			while(iterator.hasNext()) {
				String roleCode = iterator.next().toString();
				roles.add(roleCode);
			}
		}
		if(!roles.isEmpty()) {
			authorizations = authorizationService.findAuthorizationByRole(roles.get(0));
			parents = getAllParentMenu(authorizations);
			firstChilds = getFirstParentMenuChild(authorizations);
		}
		
		//获取用户信息
		if(!StringUtils.isEmptyOrWhitespace(userName)) {
			user = sideAdminUserService.findAdminUserByAdminCode(userName);
			if(user != null) {
				session.setAttribute("user_info", user);
			}
		}
		
		//用户角色权限
		mode.put("parents", parents);
		mode.put("firstChilds", firstChilds);
		return "/index/main";
	}
	
	private List<SideAuthorization> getAllParentMenu(List<SideAuthorization> authorizations){
		List<SideAuthorization> parents = new ArrayList<SideAuthorization>();
		if(!authorizations.isEmpty()) {
			for(SideAuthorization authorization : authorizations) {
				if(authorization.getMenuId().getIsParent() == 0) {
					parents.add(authorization);
				}
			}
		}
		return parents;
	}
	
	private List<SideAuthorization> getFirstParentMenuChild(List<SideAuthorization> authorizations){
		List<SideAuthorization> childs = new ArrayList<SideAuthorization>();
		if(!authorizations.isEmpty()) {
			SideAuthorization first = null;
			int index = 0;
			for(SideAuthorization authorization : authorizations) {
				if(authorization.getMenuId().getIsParent() == 0 && index == 0) {
					first = authorization;
					index++;
				}
				if(authorization.getMenuId().getIsParent() == 1) {
					if(authorization.getMenuId().getParentMenu().getMenuId() == first.getMenuId().getMenuId()) {
						childs.add(authorization);
					}
				} else {
					continue;
				}
			}
		}
		return childs;
	}
	
}
