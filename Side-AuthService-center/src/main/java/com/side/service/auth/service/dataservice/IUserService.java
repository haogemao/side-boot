/**
 * 
 */
package com.side.service.auth.service.dataservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.side.service.auth.dao.IAdminUserRepository;
import com.side.service.auth.pojo.AdminUser;

/**
 * @author gmc
 *
 */

@Service("userService")
public class IUserService {

	@Autowired
	IAdminUserRepository adminUserPosoitory;
	
	public AdminUser findByAdminCode(String adminCode) {
		return adminUserPosoitory.findByAdmincode(adminCode);
	}
	
}
