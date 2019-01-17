/**
 * 
 */
package com.side.admin.serviceImpl;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.side.admin.IDao.ISideUserDao;
import com.side.admin.IService.ISideUserService;
import com.side.admin.pojo.SideUser;
import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;

/**
 * @author gmc
 *
 */
@Service("sideUserService")
public class SideUserServiceImpl extends SideBasicServiceImpl<SideUser> implements ISideUserService {

	@Resource
	private ISideUserDao sideUserDao;
	
	@Override
	public SideUser findSideUserByCode(String code) {
		
		SideUser adminUser = null;
		
		DetachedCriteriaTS<SideUser> criteria = new DetachedCriteriaTS<SideUser>(SideUser.class);
		
		if(!StringUtils.isNullOrEmpty(code)) {
			criteria.add(Restrictions.eq("adminCode", code));
			adminUser = sideUserDao.find(criteria);
			System.out.println("用户名:" + adminUser.getAccount().getAccName() + "&密码:" + adminUser.getAccount().getAccPassword());
		}
		
		return adminUser;
	}


}
