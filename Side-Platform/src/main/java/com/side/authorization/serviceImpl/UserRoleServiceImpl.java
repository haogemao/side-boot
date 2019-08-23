/**
 * 
 */
package com.side.authorization.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.side.authorization.IDao.IUserRoleDao;
import com.side.authorization.IService.IUserRoleService;
import com.side.authorization.dto.SideRoleUserDto;
import com.side.authorization.pojo.SideUserRole;
import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.role.IRoleService.IRoleService;
import com.side.role.pojo.SideRole;
import com.side.users.IService.ISideUserService;
import com.side.users.pojo.SideUser;

/**
 * @author gmc
 *
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends SideBasicServiceImpl<SideUserRole> implements IUserRoleService {
	
	@Autowired
	@Qualifier("userRoleDao")
	private IUserRoleDao userRoleDao;
	
	@Qualifier("sideUserService")
	@Autowired
	private ISideUserService sideUserService;
	
	@Qualifier("roleService")
	@Autowired
	private IRoleService roleService;

	@Override
	public List<SideUserRole> findUserRoleByUserId(Long userId) {

		List<SideUserRole> userRoleList = null;
		if(userId > 0) {
			DetachedCriteriaTS<SideUserRole> criteria = new DetachedCriteriaTS<SideUserRole>(SideUserRole.class);
			criteria.getCriteria().createAlias("userId", "user");
			criteria.add(Restrictions.eqOrIsNull("user.userId", userId));
			userRoleList = userRoleDao.findAll(criteria);
		}
		return userRoleList;
	}
	
	@Override
	@Transactional
	public void saveUserRole(List<SideRoleUserDto> list) throws Exception {
		
		List<SideUserRole> userRoleList = new ArrayList<SideUserRole>();
		
		if(list != null && list.size() > 0) {
			
			DetachedCriteriaTS<SideUserRole> criteria = new DetachedCriteriaTS<SideUserRole>(SideUserRole.class);
			criteria.getCriteria().createAlias("userId", "user");
			criteria.add(Restrictions.eqOrIsNull("user.userId", list.get(0).getUserId()));
			
			List<SideUserRole> delUserRoleList = userRoleDao.findAll(criteria);
			
			if(delUserRoleList != null && delUserRoleList.size() > 0) {
				userRoleDao.deleteAll(delUserRoleList);
			}
			
			for(SideRoleUserDto dto : list) {
				SideUserRole userRoleTmp = new SideUserRole();
				SideUser user = sideUserService.get(SideUser.class, dto.getUserId());
				SideRole role = roleService.get(SideRole.class, dto.getRoleId());
				userRoleTmp.setRoleId(role);
				userRoleTmp.setUserId(user);
				userRoleList.add(userRoleTmp);
			}
			
			userRoleDao.saveOrUpdateAll(userRoleList);
		}
	}
}
