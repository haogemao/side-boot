/**
 * 
 */
package com.side.role.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.side.basic.constant.SideConstant;
import com.side.basic.controller.SideBaseController;
import com.side.basic.dto.ResultDto;
import com.side.basic.dto.UserInfo;
import com.side.role.IRoleService.IRoleService;
import com.side.role.dto.RoleDto;
import com.side.role.pojo.SideRole;

/**
 * @author gmc
 * @see 角色控制器
 */

@RestController
@RequestMapping("/role")
public class RoleController extends SideBaseController{
	
	@Qualifier("roleService")
	@Autowired
	private IRoleService roleService;

	@RequestMapping("/list")
	public ResultDto<SideRole> getList(RoleDto dto){
		
		ResultDto<SideRole> result = new ResultDto<SideRole>();
		try {
			List<SideRole> list = roleService.findRoleByKey(dto);
			
			if(!ObjectUtils.isEmpty(list)) {
				result.setRetCode(SideConstant.SUCCESS);
				result.setRecord(list);
			} else {
				result.setRetCode(SideConstant.QUERY_EMPTY);
				result.setRetMsg(SideConstant.QUERY_EMPTY_MSG);
			}
		} catch(Exception e) {
			result.setRetCode(SideConstant.FAIL);
			result.setRetMsg(SideConstant.FAIL_MSG);
		}
		
		return result;
		
	}
	
	@RequestMapping(method=RequestMethod.POST, name="saveRole", value="saveRole")
	public ResultDto<SideRole> saveRole(RoleDto dto){
		
		UserInfo userInfo = getUserInfo();
		
		ResultDto<SideRole> result = new ResultDto<SideRole>();
		
		if (dto.getRoleId() != null) {
			dto.setLastUpdateBy(Integer.parseInt(String.valueOf(userInfo.getUser().getUserId())));
			dto.setLastUpdateDate(new Date());
		} else {
			dto.setCreateBy(Integer.parseInt(String.valueOf(userInfo.getUser().getUserId())));
			dto.setCreateDate(new Date());
		}
		
		try {
			roleService.saveRole(dto);
			result.setRetCode(SideConstant.SUCCESS);
			result.setRetMsg(SideConstant.SAVE_SUCCESS_MSG);
			
			dto = null;
			List<SideRole> list = roleService.findRoleByKey(dto);
			result.setRecord(list);
			
		} catch(Exception e) {
			e.printStackTrace();
			result.setRetCode(SideConstant.SAVE_FAIL);
			result.setRetMsg(SideConstant.SAVE_FAIL_MSG);
		}
		
		return result;
		
	}
	
	@RequestMapping("/delRole")
	public ResultDto<SideRole> delRole(RoleDto dto){
		
		ResultDto<SideRole> result = new ResultDto<SideRole>();
		try {
			roleService.delRole(dto);
			result.setRetCode(SideConstant.SUCCESS);
			result.setRetMsg(SideConstant.SUCCESS_MSG);
			
			dto = null;
			List<SideRole> list = roleService.findRoleByKey(dto);
			result.setRecord(list);
			
			
		} catch(Exception e) {
			e.printStackTrace();
			result.setRetCode(SideConstant.FAIL);
			result.setRetMsg(SideConstant.FAIL_MSG);
		}
		
		return result;
	}
	
}
