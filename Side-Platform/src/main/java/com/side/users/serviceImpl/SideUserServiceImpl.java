/**
 * 
 */
package com.side.users.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.netflix.discovery.util.StringUtil;
import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.users.IDao.ISideAccountDao;
import com.side.users.IDao.ISideUserDao;
import com.side.users.IService.ISideUserService;
import com.side.users.dto.SideUserDto;
import com.side.users.pojo.Account;
import com.side.users.pojo.SideUser;

/**
 * @author gmc
 *
 */
@Service("sideUserService")
public class SideUserServiceImpl extends SideBasicServiceImpl<SideUser> implements ISideUserService {

	@Resource
	private ISideUserDao sideUserDao;
	
	@Resource
	private ISideAccountDao sideAccountDao;
	
	@Override
	public SideUser findSideUserByCode(String code) {
		
		SideUser adminUser = null;
		
		DetachedCriteriaTS<SideUser> criteria = new DetachedCriteriaTS<SideUser>(SideUser.class);
		
		if(!StringUtils.isNullOrEmpty(code)) {
			criteria.add(Restrictions.eq("userCode", code));
			adminUser = sideUserDao.find(criteria);
		}
		
		return adminUser;
	}

	@Override
	public List<SideUser> findAllUser(SideUserDto dto) throws Exception {
		List<SideUser> list = null;
		
		DetachedCriteriaTS<SideUser> criteria = new DetachedCriteriaTS<SideUser>(SideUser.class);	
		
		if(dto.getAccount() != null) {
			Account account = sideAccountDao.get(Account.class, dto.getAccount());
			criteria.add(Restrictions.eq("account", account));
		}
		
		if(!StringUtils.isNullOrEmpty(dto.getUserCode())) {
			criteria.add(Restrictions.eq("userCode", dto.getUserCode()));
		}
		
		if(!StringUtils.isNullOrEmpty(dto.getUserName())) {
			criteria.add(Restrictions.like("userName", dto.getUserName(), MatchMode.ANYWHERE));
		}
		
		if(dto.getUserStatus() != null) {
			criteria.add(Restrictions.eq("userStatus", dto.getUserStatus()));
		}
		
		if(!StringUtils.isNullOrEmpty(dto.getKey())) {
			criteria.add(Restrictions.or(Restrictions.like("userCode", dto.getKey(), MatchMode.ANYWHERE), 
										 Restrictions.like("userName", dto.getKey(), MatchMode.ANYWHERE)));
		}
		
		list = sideUserDao.findAll(criteria);
		
		return list;
	}


}
