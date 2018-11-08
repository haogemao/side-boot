/**
 * 
 */
package com.side.authorization.IService;

import com.side.authorization.pojo.SideUserRole;
import com.side.basic.IbaseService.ISideBasicService;

/**
 * @author gmc
 *
 */
public interface IUserRoleService extends ISideBasicService<SideUserRole> {
	
	public SideUserRole findUserRoleByUserId(String userId);

}
