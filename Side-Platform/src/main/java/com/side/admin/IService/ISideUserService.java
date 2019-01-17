/**
 * 
 */
package com.side.admin.IService;

import com.side.admin.pojo.SideUser;
import com.side.basic.IbaseService.ISideBasicService;

/**
 * @author gmc
 *
 */
public interface ISideUserService extends ISideBasicService<SideUser>{

	public SideUser findSideUserByCode(String code);
}
