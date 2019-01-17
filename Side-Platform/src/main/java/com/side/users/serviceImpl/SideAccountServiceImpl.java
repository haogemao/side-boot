/**
 * 
 */
package com.side.users.serviceImpl;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.users.IDao.ISideAccountDao;
import com.side.users.IService.ISideAccountService;
import com.side.users.pojo.Account;


/**
 * @author gmc
 *
 */
@Service("sideAccountService")
public class SideAccountServiceImpl extends SideBasicServiceImpl<Account> implements ISideAccountService {
	
	@Resource
	private ISideAccountDao sideAccountDao;

	@Override
	public Account findAccountByAccCode(String accountCode) {
		
		Account account = null;
		DetachedCriteriaTS<Account> criteria = new DetachedCriteriaTS<Account>(Account.class);
		
		if(!StringUtils.isNullOrEmpty(accountCode)) {
			criteria.add(Restrictions.eq("accCode", accountCode));
			account = sideAccountDao.find(criteria);
		}
		
		return account;
	}

	

}
