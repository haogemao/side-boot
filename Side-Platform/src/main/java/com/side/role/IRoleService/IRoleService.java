/**
 * 
 */
package com.side.role.IRoleService;

import com.side.basic.IbaseService.ISideBasicService;
import com.side.role.pojo.SideRole;

/**
 * @author gmc
 *
 */
public interface IRoleService extends ISideBasicService<SideRole> {
	
	public SideRole findRoleByCode(String roleCode);
	
}
