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
	
	/**
	 * 根据角色编码查询角色权限
	 * @param roleCode
	 * @return
	 */
	public List<SideAuthorization> findAuthorizationByRole(String roleCode) throws Exception;
	
	/**
	 * 根据角色编码查询所有父级菜单权限
	 * @param roleCode
	 * @return
	 */
	public List<SideAuthorization> findParentAuthorizationByRole(String roleCode) throws Exception;

}
