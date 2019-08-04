/**
 * 
 */
package com.side.users.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.side.basic.common.utils.PageMode;
import com.side.basic.constant.SideConstant;
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
	public ResultDto<SideUserDto> getUserList(SideUserDto dto, @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize")int pageSize){
		
		ResultDto<SideUserDto> resultDto = new ResultDto<SideUserDto>();
		PageMode<SideUserDto> pageMode = null;
		
		try {
			pageMode = sideUserService.findSystemUserBySQL(dto, pageNumber, pageSize);
			
			if(pageMode != null) {
				resultDto.setRetCode(SideConstant.SUCCESS);
				resultDto.setRetMsg(SideConstant.SUCCESS_MSG);
				resultDto.setPageMode(pageMode);
			} else {
				resultDto.setRetCode(SideConstant.QUERY_EMPTY);
				resultDto.setRetMsg(SideConstant.QUERY_EMPTY_MSG);
			}
			
		} catch(Exception e) {
			resultDto.setRetCode(SideConstant.FAIL);
			resultDto.setRetMsg(SideConstant.FAIL_MSG);
			e.printStackTrace();
		}
		
		return resultDto;
	}
	
	@PostMapping("/userEditer")
	public ResultDto<SideUser> userEdit(SideUser user){
		
		ResultDto<SideUser> resultDto = new ResultDto<SideUser>();
		
		PageMode<SideUser> pageMode = null;
		
		SideUserDto dto = new SideUserDto();
		
		try {
			if(user != null) {
				user.setCreateBy(getUserInfo().getUser().getUserId());
				user.setCreateDate(new Date());
				user.getAccount().setCreateBy(user.getCreateBy());
				user.getAccount().setCreateDate(new Date());
			}
			sideUserService.update(user);
			
			pageMode = sideUserService.findUserForPages(dto, 1, 10);
			
			if(pageMode != null) {
				resultDto.setRetCode(SideConstant.SUCCESS);
				resultDto.setRetMsg(SideConstant.SUCCESS_MSG);
				resultDto.setPageMode(pageMode);
			} else {
				resultDto.setRetCode(SideConstant.QUERY_EMPTY);
				resultDto.setRetMsg(SideConstant.QUERY_EMPTY_MSG);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setRetCode(SideConstant.FAIL);
			resultDto.setRetMsg(SideConstant.FAIL_MSG);
		}
		
		
		return resultDto;
	}
	
}
