/**
 * 
 */
package com.side.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.side.basic.controller.SideBaseController;
import com.side.basic.dto.ResultDto;
import com.side.users.IService.ISideUserService;
import com.side.users.dto.SideUserDto;
import com.side.users.pojo.SideUser;

/**
 * @author gmc
 * 
 */
@RestController
@RequestMapping("/users")
public class UserController extends SideBaseController {
	
	@Qualifier("sideUserService")
	@Autowired
	private ISideUserService sideUserService;

	@GetMapping("/getUserList")
	public ResultDto<SideUser> getUserList(SideUserDto dto){
		
		return null;
	}
	
}
