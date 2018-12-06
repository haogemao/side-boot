/**
 * 
 */
package com.side.admin.serviceImpl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.side.admin.IDao.ISideAdminUserDao;
import com.side.admin.IService.ISideAdminUserService;
import com.side.admin.pojo.AdminUser;
import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;

/**
 * @author gmc
 *
 */
@Service("sideAdminUserService")
public class SideAdminUserServiceImpl extends SideBasicServiceImpl<AdminUser> implements ISideAdminUserService {

	@Resource
	private ISideAdminUserDao sideAdminUserDao;
	
	@Override
	public AdminUser findAdminUserByAdminCode(String adminCode) {
		
		AdminUser adminUser = null;
		
		DetachedCriteriaTS<AdminUser> criteria = new DetachedCriteriaTS<AdminUser>(AdminUser.class);
		
		if(!StringUtils.isNullOrEmpty(adminCode)) {
			criteria.add(Restrictions.eq("adminCode", adminCode));
			adminUser = sideAdminUserDao.find(criteria);
			System.out.println("用户名:" + adminUser.getAccount().getAccName() + "&密码:" + adminUser.getAccount().getAccPassword());
		}
		
		return adminUser;
	}


}
