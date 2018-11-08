/**
 * 
 */
package com.side.users.IUserService;

import com.side.basic.IbaseService.ISideBasicService;
import com.side.users.pojo.SideUsers;

/**
 * @author gmc
 *
 */
public interface ISideUserService extends ISideBasicService<SideUsers> {

	public SideUsers findUserByCode(String code);
}
