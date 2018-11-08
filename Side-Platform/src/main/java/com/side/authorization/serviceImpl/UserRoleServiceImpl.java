/**
 * 
 */
package com.side.authorization.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.side.authorization.IDao.IUserRoleDao;
import com.side.authorization.IService.IUserRoleService;
import com.side.authorization.pojo.SideUserRole;
import com.side.basic.baseServiceImpl.SideBasicServiceImpl;

/**
 * @author gmc
 *
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends SideBasicServiceImpl<SideUserRole> implements IUserRoleService {
	
	@Autowired
	@Qualifier("userRoleDao")
	private IUserRoleDao userRoleDao;

	@Override
	public SideUserRole findUserRoleByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
