/**
 * 
 */
package com.side.role.roleServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.role.IRoleDao.IRoleDao;
import com.side.role.IRoleService.IRoleService;
import com.side.role.dto.RoleDto;
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

	@Override
	public List<SideRole> findRoleByKey(RoleDto dto) {
		
		DetachedCriteriaTS<SideRole> criteria = new DetachedCriteriaTS<SideRole>(SideRole.class);
		
		if(!StringUtils.isNullOrEmpty(dto.getKey())) {
			criteria.add(Restrictions.or(Restrictions.like("roleName", dto.getKey(), MatchMode.ANYWHERE), 
										 Restrictions.like("roleCode", dto.getKey(), MatchMode.ANYWHERE)));
		}
		
		return roleDao.findAll(criteria);
	}
	
}
