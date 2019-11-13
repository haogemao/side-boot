/**
 * 
 */
package com.side.role.IRoleService;

import java.util.List;

import com.side.basic.IbaseService.ISideBasicService;
import com.side.role.dto.RoleDto;
import com.side.role.pojo.SideRole;

/**
 * @author gmc
 *
 */
public interface IRoleService extends ISideBasicService<SideRole> {
	
	public SideRole findRoleByCode(String roleCode) throws Exception;
	
	public List<SideRole> findRoleByKey(RoleDto dto)  throws Exception;
	
	public void saveRole(RoleDto dto)  throws Exception;
	
	public void delRole(RoleDto dto) throws Exception;
	
}
