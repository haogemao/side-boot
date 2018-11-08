/**
 * 
 */
package com.side.users.userServiceImpl;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.users.IUserDao.ISideUserDao;
import com.side.users.IUserService.ISideUserService;
import com.side.users.pojo.SideUsers;

/**
 * @author gmc
 *
 */
@Service("sideUserService")
public class SideUserServiceImpl extends SideBasicServiceImpl<SideUsers> implements ISideUserService {
	
	@Resource
	private ISideUserDao sideUserDao;

	@Override
	public SideUsers findUserByCode(String code) {
		
		SideUsers user = null;
		
		DetachedCriteriaTS<SideUsers> criteria = new DetachedCriteriaTS<SideUsers>(SideUsers.class);
		
		if(!StringUtils.isNullOrEmpty(code)) {
			criteria.add(Restrictions.eq("userCode", code));
			user = sideUserDao.find(criteria);
		}
		
		return user;
	}

}
