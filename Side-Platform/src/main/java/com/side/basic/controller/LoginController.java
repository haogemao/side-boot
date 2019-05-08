/**
 * 
 */
package com.side.basic.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.side.authorization.IService.IAuthorizationService;
import com.side.authorization.IService.IUserRoleService;
import com.side.authorization.pojo.SideAuthorization;
import com.side.menus.pojo.SideMenus;
import com.side.users.IService.ISideAccountService;
import com.side.users.IService.ISideUserService;
import com.side.users.pojo.SideUser;

/**
 * @author gmc
 *
 */
@RestController
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

	@RequestMapping(value="/dologin")
	public Map<String, Object> doLogin(ModelMap mode, Principal principal, HttpSession session) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<SideAuthorization> authorizations = null;
		List<SideMenus> parents = null;
		List<SideMenus> firstChilds = null;
		
		//获取用户名
		String userName = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取角色
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> roleList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<String> roles = new ArrayList<String>();
		SideUser user = null;
		try {
			if(!roleList.isEmpty()) {
				Iterator<GrantedAuthority> iterator =  roleList.iterator();
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
					result.put("user_info", user);
				}
			}
			
			//用户角色权限
			result.put("parents", parents);
			result.put("firstChilds", firstChilds);
			return result;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private List<SideMenus> getAllParentMenu(List<SideAuthorization> authorizations){
		List<SideMenus> parents = new ArrayList<SideMenus>();
		if(!authorizations.isEmpty()) {
			for(SideAuthorization authorization : authorizations) {
				if(authorization.getMenuId().getIsParent() == 0) {
					parents.add(authorization.getMenuId());
				}
			}
		}
		return parents;
	}
	
	private List<SideMenus> getFirstParentMenuChild(List<SideAuthorization> authorizations){
		List<SideMenus> childs = new ArrayList<SideMenus>();
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
						childs.add(authorization.getMenuId());
					}
				} else {
					continue;
				}
			}
		}
		return childs;
	}
	
}
