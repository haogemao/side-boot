/**
 * 
 */
package com.side.service.auth.service.dataservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.side.service.auth.dao.IUserRoleRepository;
import com.side.service.auth.pojo.SideUser;
import com.side.service.auth.pojo.SideUserRole;

/**
 * @author gmc
 *
 */
@Service("userRoleService")
public class IUserRoleService {
	
	@Autowired
	private IUserRoleRepository userRoleRepository;

	public List<SideUserRole> findByUserId(SideUser userId){
		return userRoleRepository.findByUserId(userId);
	}
}
