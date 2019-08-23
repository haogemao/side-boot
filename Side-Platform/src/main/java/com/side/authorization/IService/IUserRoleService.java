/**
 * 
 */
package com.side.authorization.IService;

import java.util.List;

import com.side.authorization.dto.SideRoleUserDto;
import com.side.authorization.pojo.SideUserRole;
import com.side.basic.IbaseService.ISideBasicService;

/**
 * @author gmc
 *
 */
public interface IUserRoleService extends ISideBasicService<SideUserRole> {
	
	public List<SideUserRole> findUserRoleByUserId(Long userId);
	
	public void saveUserRole(List<SideRoleUserDto> list) throws Exception;

}
