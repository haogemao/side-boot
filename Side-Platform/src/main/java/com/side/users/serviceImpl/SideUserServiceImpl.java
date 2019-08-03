/**
 * 
 */
package com.side.users.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.basic.common.utils.PageMode;
import com.side.users.IDao.ISideAccountDao;
import com.side.users.IDao.ISideUserDao;
import com.side.users.IService.ISideUserService;
import com.side.users.dto.SideUserDto;
import com.side.users.pojo.Account;
import com.side.users.pojo.SideUser;
import com.side.users.sql.UserSQL;

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
		
		if(!StringUtils.isNullOrEmpty(dto.getSearchKey())) {
			criteria.add(Restrictions.or(Restrictions.like("userCode", dto.getSearchKey(), MatchMode.ANYWHERE), 
										 Restrictions.like("userName", dto.getSearchKey(), MatchMode.ANYWHERE)));
		}
		
		list = sideUserDao.findAll(criteria);
		
		return list;
	}

	@Override
	public PageMode<SideUser> findUserForPages(SideUserDto dto, int pageNumber, int pageSize) throws Exception {
		
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
		
		if(!StringUtils.isNullOrEmpty(dto.getSearchKey())) {
			criteria.add(Restrictions.or(Restrictions.like("userCode", dto.getSearchKey(), MatchMode.ANYWHERE), 
										 Restrictions.like("userName", dto.getSearchKey(), MatchMode.ANYWHERE)));
		}
		
		return sideUserDao.findForPage(criteria, pageNumber, pageSize);
	}

	@Override
	public void userEditer(SideUser user) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//新增
		if(user != null && user.getUserId() == null) {
			if(user.getAccount() != null) {
				String tmpPwd = user.getAccount().getAccPassword();
				user.getAccount().setAccPassword(encoder.encode(tmpPwd));
				sideUserDao.saveOrUpdate(user);
			}
		} else { //修改
			SideUser old = sideUserDao.get(SideUser.class, user.getUserId());
			BeanUtils.copyProperties(user, old, 
					"userId", "userCode", "createDate", "lastUpdateDate", "createBy", "lastUpdateBy", 
					"account.accountId", "account.accCode", "account.userId", "account.createDate", "account.createBy",
					"account.lastUpdateDate", "account.lastUpdateBy");
			if(user.getAccount() != null && user.getAccount().getAccPassword() != null) {
				if(!encoder.matches(user.getAccount().getAccPassword(), old.getAccount().getAccPassword())) {
					old.getAccount().setAccPassword(encoder.encode(user.getAccount().getAccPassword()));
				}
				old.getAccount().setLastUpdateBy(user.getUserId());
				old.getAccount().setLastUpdateDate(new Date());
			}
			old.setLastUpdateBy(user.getUserId());
			old.setLastUpdateDate(new Date());
			sideUserDao.saveOrUpdate(old);
		}
		
	}

	@Override
	public PageMode<SideUser> findSystemUserBySQL(SideUserDto dto, int pageNumber, int pageSize) throws Exception {
		
		sideUserDao.findBySQL(UserSQL.FIND_SYSTEM_USER_BYSQL, params, pageNumber, pageSize, clazz)
		
		return null;
	}


}
