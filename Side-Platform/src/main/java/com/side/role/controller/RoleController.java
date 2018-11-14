/**
 * 
 */
package com.side.role.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gmc
 * @see 角色控制器
 */

@Controller
@RequestMapping("/role")
public class RoleController {

	@RequestMapping("manager")
	public String toPage() {
		return "system/sys_roles";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> getList(){
		
		return null;
	}
}
