/**
 * 
 */
package com.side.service.auth.service.dataservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.side.service.auth.dao.ISideUserRepository;
import com.side.service.auth.pojo.SideUser;

/**
 * @author gmc
 *
 */

@Service("userService")
public class IUserService {

	@Autowired
	ISideUserRepository sideUserPosoitory;
	
	public SideUser findByUserCode(String userCode) {
		return sideUserPosoitory.findByUsercode(userCode);
	}
	
}
