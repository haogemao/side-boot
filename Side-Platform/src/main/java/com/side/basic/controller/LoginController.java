/**
 * 
 */
package com.side.basic.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.side.admin.IService.ISideAccountService;
import com.side.admin.IService.ISideUserService;
import com.side.admin.pojo.SideUser;
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
	@Qualifier("sideUserService")
	private ISideUserService sideUserService;
	
	@Autowired
	@Qualifier("sideAccountService")
	private ISideAccountService sideAccountService;
	
	@Autowired
	@Qualifier("authorizationService")
	private IAuthorizationService authorizationService;
	
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;

	@RequestMapping(value="dologin", name="dologin", path="dologin", method=RequestMethod.GET)
	public String doLogin(ModelMap mode, Principal principal, HttpSession session) {
		
		List<SideAuthorization> authorizations = null;
		List<SideAuthorization> parents = null;
		List<SideAuthorization> firstChilds = null;
		
		//获取用户名
		String userName = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取角色
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> roleList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<String> roles = new ArrayList<String>();
		SideUser user = null;
		
		if(!roleList.isEmpty()) {
			Iterator iterator =  roleList.iterator();
			while(iterator.hasNext()) {
				String roleCode = iterator.next().toString();
				roles.add(roleCode);
			}
		}
		//获取用户权限
		if(!roles.isEmpty()) {
			authorizations = authorizationService.findAuthorizationByRole(roles.get(0));
			parents = getAllParentMenu(authorizations);
			firstChilds = getFirstParentMenuChild(authorizations);
		}
		
		//获取用户信息
		if(!StringUtils.isEmpty(userName)) {
			user = sideUserService.findSideUserByCode(userName);
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
