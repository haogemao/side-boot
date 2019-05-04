/**
 * 
 */
package com.side.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.side.basic.constant.SideConstant;
import com.side.basic.dto.ResultDto;
import com.side.role.IRoleService.IRoleService;
import com.side.role.dto.RoleDto;
import com.side.role.pojo.SideRole;

/**
 * @author gmc
 * @see 角色控制器
 */

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Qualifier("roleService")
	@Autowired
	private IRoleService roleService;

	@RequestMapping("/list")
	public ResultDto<SideRole> getList(RoleDto dto){
		
		ResultDto<SideRole> result = new ResultDto<SideRole>();
		
		List<SideRole> list = roleService.findRoleByKey(dto);
		
		if(!ObjectUtils.isEmpty(list)) {
			result.setRetCode(SideConstant.QUERY_SUCCESS);
			result.setRecord(list);
		} else {
			result.setRetCode(SideConstant.QUERY_EMPTY);
			result.setRetMsg(SideConstant.QUERY_EMPTY_MSG);
		}
		
		return result;
		
	}
	
}
