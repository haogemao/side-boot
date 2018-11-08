/**
 * 
 */
package com.side.users.userServiceImpl;

import org.springframework.stereotype.Service;

import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.users.IUserService.ISideUserAccountService;
import com.side.users.pojo.SideUserAccount;

/**
 * @author gmc
 *
 */
@Service("sideUserAccountService")
public class SideUserAccountServiceImpl extends SideBasicServiceImpl<SideUserAccount> implements ISideUserAccountService {


}
