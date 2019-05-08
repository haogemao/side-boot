/**
 * 
 */
package com.side.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import com.side.basic.dto.UserInfo;
import com.side.users.IService.ISideUserService;
import com.side.users.pojo.SideUser;


/**
 * @author gmc
 * @see 基础控制器
 */

@RestController
public class SideBaseController {
	
	@Autowired
	@Qualifier("sideUserService")
	private ISideUserService sideUserService;

	protected UserInfo getUserInfo() {
		UserInfo userInfo = new UserInfo();
		
		String userName = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!StringUtils.isEmpty(userName)) {
			SideUser user = sideUserService.findSideUserByCode(userName);
			if(user != null) {
				userInfo.setUser(user);
			}
		}
		
		return userInfo;
	}
}
