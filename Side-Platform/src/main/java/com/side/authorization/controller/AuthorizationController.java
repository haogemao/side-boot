/**
 * 
 */
package com.side.authorization.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.side.authorization.IService.IAuthorizationService;
import com.side.authorization.dto.SideAuthorizationDto;
import com.side.authorization.pojo.SideAuthorization;
import com.side.basic.constant.SideConstant;
import com.side.basic.controller.SideBaseController;
import com.side.basic.dto.ResultDto;
import com.side.menus.IService.ISideMenuService;
import com.side.role.IRoleService.IRoleService;

/**
 * @author gmc
 * @see 负责处理角色权限
 */
@RestController
@RequestMapping("/authorization")
public class AuthorizationController extends SideBaseController {
	
	@Qualifier("authorizationService")
	@Autowired
	private IAuthorizationService authorizationService;
	
	@Qualifier("sideMenuService")
	@Autowired
	private ISideMenuService sideMenuService;
	
	@Qualifier("roleService")
	@Autowired
	private IRoleService roleService;
	
	/**
	 * 查询角色权限
	 * @param authorization
	 * @return
	 */
	@GetMapping("/roleInfo")
	public ResultDto<SideAuthorization> findAuthorizationByMenuAndRole(SideAuthorizationDto authorization) {
		ResultDto<SideAuthorization> result = new ResultDto<SideAuthorization>();
		try {
			List<SideAuthorization> list = authorizationService.findAuthorizationByRoleAndMenu(authorization);
			result.setRetCode(SideConstant.SUCCESS);
			result.setRecord(list);
			result.setRetMsg(SideConstant.SUCCESS_MSG);
		} catch(Exception e) {
			result.setRetCode(SideConstant.FAIL);
			result.setRetMsg(SideConstant.FAIL_MSG);
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 保存角色权限
	 * @param authorizationDto
	 * @return
	 */
	@PostMapping("/saveAuthorization")
	public ResultDto<SideAuthorization> saveAuthorization(@RequestBody List<SideAuthorizationDto> authorization) {
		ResultDto<SideAuthorization> result = new ResultDto<SideAuthorization>();
		try {
			authorizationService.saveAllAuthorizations(authorization);
			result.setRetCode(SideConstant.SUCCESS);
			result.setRecord(null);
			result.setRetMsg(SideConstant.SUCCESS_MSG);
		} catch(Exception e) {
			result.setRetCode(SideConstant.FAIL);
			result.setRetMsg(SideConstant.FAIL_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	@GetMapping("/delAuthorization")
	public ResultDto<SideAuthorization> delAuthorization(SideAuthorizationDto authorizationDto) {
		ResultDto<SideAuthorization> result = new ResultDto<SideAuthorization>();
		
		return result;
	}
	
}
