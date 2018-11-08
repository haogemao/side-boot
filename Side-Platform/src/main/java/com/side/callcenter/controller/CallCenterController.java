/**
 * 
 */
package com.side.callcenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.side.users.IUserService.ISideUserService;
	
/**
 * @author gmc
 *
 */

@Controller
@RequestMapping("/callcenter")
public class CallCenterController {
	
	@Autowired
	@Qualifier("sideUserService")
	private ISideUserService sideUserService;
	
	@RequestMapping("/toCallCenter")
	public String toCallCenter() {
		return "callcenter/kefu";
	}

	@RequestMapping("/get_user_info")
	@ResponseBody
	public String getUserInfo() {
		
		return null;
	}
	
}
