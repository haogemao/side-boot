/**
 * 
 */
package com.side.authorization.IService;

import java.util.List;

import com.side.authorization.pojo.SideAuthorization;
import com.side.basic.IbaseService.ISideBasicService;

/**
 * @author gmc
 *
 */
public interface IAuthorizationService extends ISideBasicService<SideAuthorization> {
	
	public List<SideAuthorization> findAuthorizationByRole(String roleCode);

}
