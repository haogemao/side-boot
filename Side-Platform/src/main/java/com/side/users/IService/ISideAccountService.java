/**
 * 
 */
package com.side.users.IService;

import com.side.basic.IbaseService.ISideBasicService;
import com.side.users.pojo.Account;

/**
 * @author gmc
 *
 */
public interface ISideAccountService extends ISideBasicService<Account> {

	public Account findAccountByAccCode(String accountCode);
}
