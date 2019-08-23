/**
 * 
 */
package com.side.authorization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.side.authorization.IService.IUserRoleService;
import com.side.authorization.dto.SideRoleUserDto;
import com.side.authorization.pojo.SideUserRole;
import com.side.basic.constant.SideConstant;
import com.side.basic.controller.SideBaseController;
import com.side.basic.dto.ResultDto;

/**
 * @author gmc
 * @see 用户角色关系维护
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController extends SideBaseController {
	
	@Qualifier("userRoleService")
	@Autowired
	private IUserRoleService userRoleService;

	@GetMapping("/getUserRole")
	public ResultDto<SideUserRole> getRoleByUser(@RequestParam("userId") Long userId){
		ResultDto<SideUserRole> resultDto = new ResultDto<SideUserRole>();
		try {
			resultDto.setRecord(userRoleService.findUserRoleByUserId(userId));
			resultDto.setRetCode(SideConstant.SUCCESS);
			resultDto.setRetMsg(SideConstant.SUCCESS_MSG);
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setRetCode(SideConstant.FAIL);
			resultDto.setRetMsg(SideConstant.FAIL_MSG);
		}
		
		return resultDto;
	}
	
	@PostMapping("/saveRoleUser")
	public ResultDto<SideUserRole> saveRoleUser(@RequestBody List<SideRoleUserDto> dtoList){
		ResultDto<SideUserRole> resultDto = new ResultDto<SideUserRole>();
		try {
			userRoleService.saveUserRole(dtoList);
			resultDto.setRetCode(SideConstant.SUCCESS);
			resultDto.setRetMsg(SideConstant.SUCCESS_MSG);
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setRetCode(SideConstant.FAIL);
			resultDto.setRetMsg(SideConstant.FAIL_MSG);
		}
		return resultDto;
	}
}
