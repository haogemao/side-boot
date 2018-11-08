/**
 * 
 */
package com.side.role.roleServiceImpl;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.role.IRoleDao.IRoleDao;
import com.side.role.IRoleService.IRoleService;
import com.side.role.pojo.SideRole;

/**
 * @author gmc
 *
 */
@Service("roleService")
public class RoleServiceImpl extends SideBasicServiceImpl<SideRole> implements IRoleService {

	@Autowired
	@Qualifier("roleDao")
	private IRoleDao roleDao;

	@Override
	public SideRole findRoleByCode(String roleCode) {
		
		SideRole role = null;
		
		DetachedCriteriaTS<SideRole> criteria = new DetachedCriteriaTS<SideRole>(SideRole.class);
		
		if(!StringUtils.isNullOrEmpty(roleCode)) {
			criteria.add(Restrictions.eq("roleCode", roleCode));
			role = roleDao.find(criteria);
		}
		
		return role;
	}
}
