/**
 * 
 */
package com.side.users.IService;

import com.side.basic.IbaseService.ISideBasicService;
import com.side.users.pojo.SideUser;

/**
 * @author gmc
 *
 */
public interface ISideUserService extends ISideBasicService<SideUser>{

	public SideUser findSideUserByCode(String code);
}
