/**
 * 
 */
package com.side.authorization.serviceImpl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.side.authorization.IDao.IAuthorizationDao;
import com.side.authorization.IService.IAuthorizationService;
import com.side.authorization.pojo.SideAuthorization;
import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.role.IRoleService.IRoleService;
import com.side.role.pojo.SideRole;

/**
 * @author gmc
 *
 */
@Service("authorizationService")
public class AuthorizationServiceImpl extends SideBasicServiceImpl<SideAuthorization> implements IAuthorizationService {
	
	@Autowired
	@Qualifier("authorizationDao")
	private IAuthorizationDao authorizationDao;
	
	@Autowired
	@Qualifier("roleService")
	private IRoleService roleService;

	@Override
	public List<SideAuthorization> findAuthorizationByRole(String roleCode) throws Exception {
		List<SideAuthorization> authorizations = null;
		SideRole role = roleService.findRoleByCode(roleCode);
		if(role != null) {
			DetachedCriteriaTS<SideAuthorization> criteria = new DetachedCriteriaTS<SideAuthorization>(SideAuthorization.class);
			criteria.add(Restrictions.eqOrIsNull("roleId", role));
			criteria.addOrder(Order.asc("menuId"));
			authorizations = authorizationDao.findAll(criteria);
		}
		return authorizations;
	}

	@Override
	public List<SideAuthorization> findParentAuthorizationByRole(String roleCode) throws Exception {
		DetachedCriteriaTS<SideAuthorization> criteria = new DetachedCriteriaTS<SideAuthorization>(SideAuthorization.class);
		if(StringUtils.isEmpty(roleCode)) {
			return null;
		}
		criteria.getCriteria().createAlias("roleId", "role");
		criteria.getCriteria().createAlias("menuId", "menu");
		
		criteria.add(Restrictions.eq("role.roleCode", roleCode));
		criteria.add(Restrictions.eq("menu.isParent", 0));
		
		return authorizationDao.findAll(criteria);
		
	}
}
