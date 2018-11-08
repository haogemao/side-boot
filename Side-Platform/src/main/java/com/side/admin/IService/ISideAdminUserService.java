/**
 * 
 */
package com.side.admin.IService;

import com.side.admin.pojo.AdminUser;
import com.side.basic.IbaseService.ISideBasicService;

/**
 * @author gmc
 *
 */
public interface ISideAdminUserService extends ISideBasicService<AdminUser>{

	public AdminUser findAdminUserByAdminCode(String adminCode);
}
