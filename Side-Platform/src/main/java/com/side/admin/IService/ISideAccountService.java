/**
 * 
 */
package com.side.admin.IService;

import com.side.admin.pojo.Account;
import com.side.basic.IbaseService.ISideBasicService;

/**
 * @author gmc
 *
 */
public interface ISideAccountService extends ISideBasicService<Account> {

	public Account findAccountByAccCode(String accountCode);
}
