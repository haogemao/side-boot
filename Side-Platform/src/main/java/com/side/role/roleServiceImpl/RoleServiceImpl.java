/**
 * 
 */
package com.side.role.roleServiceImpl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
	public SideRole findRoleByCode(String roleCode) throws Exception {
		
		SideRole role = null;
		
		DetachedCriteriaTS<SideRole> criteria = new DetachedCriteriaTS<SideRole>(SideRole.class);
		
		if(!StringUtils.isNullOrEmpty(roleCode)) {
			criteria.add(Restrictions.eq("roleCode", roleCode));
			role = roleDao.find(criteria);
		}
		
		return role;
	}

	@Override
	public List<SideRole> findRoleByKey(RoleDto dto) throws Exception{
		
		DetachedCriteriaTS<SideRole> criteria = new DetachedCriteriaTS<SideRole>(SideRole.class);
		
		if(!ObjectUtils.isEmpty(dto)) {
			
			if ( !StringUtils.isNullOrEmpty( dto.getKey() ) ) {
				criteria.add(Restrictions.or(Restrictions.like("roleName", dto.getKey(), MatchMode.ANYWHERE), 
											 Restrictions.like("roleCode", dto.getKey(), MatchMode.ANYWHERE)));
			} else {
				
				if(!StringUtils.isNullOrEmpty(dto.getRoleCode())) {
					criteria.add(Restrictions.like("roleCode", dto.getRoleCode(), MatchMode.ANYWHERE));
				}
				
				if(!StringUtils.isNullOrEmpty(dto.getRoleCode())) {
					criteria.add(Restrictions.like("roleName", dto.getRoleName(), MatchMode.ANYWHERE));
				}
				
				if(dto.getRoleId() != null) {
					criteria.add(Restrictions.eq("roleId", dto.getRoleId()));
				}
				
				if(dto.getRoleStatus() != null) {
					criteria.add(Restrictions.eq("roleStatus", dto.getRoleStatus()));
				}
				
			}
			
		} 
		
		return roleDao.findAll(criteria);
	}

	@Override
	@Transactional
	public void saveRole(RoleDto dto) throws Exception{
		//roleId不为空时属于编辑、修改操作
		if(dto.getRoleId() != null) {
			
			SideRole role = roleDao.get(SideRole.class, dto.getRoleId());
			
			if(!ObjectUtils.isEmpty(role)) {
				role.setRoleCode(dto.getRoleCode());
				role.setRoleName(dto.getRoleName());
				role.setRoleStatus(dto.getRoleStatus());
				role.setLastUpdateBy(dto.getLastUpdateBy());
				role.setLastUpdateDate(dto.getLastUpdateDate());
				roleDao.saveOrUpdate(role);
			}
			
		} else {
			SideRole role = new SideRole(dto.getRoleCode(), 
											dto.getRoleName(), 
											dto.getRoleStatus(), 
											dto.getCreateDate(), 
											dto.getCreateBy());
			roleDao.save(role);
		}
		
	}

	@Override
	@Transactional
	public void delRole(RoleDto dto) throws Exception {
		if(dto.getRoleId() != null) {
			SideRole role = roleDao.get(SideRole.class, dto.getRoleId());
			if(role != null) {
				roleDao.delete(role);
			}
		}
	}
	
}
