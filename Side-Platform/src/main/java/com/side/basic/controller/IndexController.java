/**
 * 
 */
package com.side.basic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.side.authorization.IService.IAuthorizationService;
import com.side.authorization.pojo.SideAuthorization;
import com.side.menus.IService.ISideMenuService;
import com.side.menus.pojo.SideMenus;
import com.side.users.IService.ISideUserService;
import com.side.users.pojo.SideUser;

/**
 * @author gmc
 * @see 主业数据rest接口
 */

@RestController
public class IndexController {
	
	@Autowired
	@Qualifier("authorizationService")
	private IAuthorizationService authorizationService;
	
	@Autowired
	@Qualifier("sideMenuService")
	private ISideMenuService sideMenuService;
	
	@Autowired
	@Qualifier("sideUserService")
	private ISideUserService userService;

	@RequestMapping("/getAllParentMenus")
	public List<SideAuthorization> getAllParentMenus() {
		List<SideAuthorization> authorizationList = new ArrayList<SideAuthorization>();
		try {
			@SuppressWarnings("unchecked")
			List<GrantedAuthority> roleList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			if(!roleList.isEmpty()) {
				authorizationList = authorizationService.findParentAuthorizationByRole(roleList.get(0).getAuthority());
			}
			return authorizationList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@RequestMapping("/getChildParentMenus")
	public List<SideMenus> getChildMenuByParent(@RequestParam("parentId") int parentId){
		try {
			return sideMenuService.getChildByParentId(parentId);
		} catch(Exception e) {
			return null;
		}
	}
	
	@RequestMapping("/getUserInfo")
	public SideUser getUsers() {
		String userCode = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.findSideUserByCode(userCode);
	}
	
}
