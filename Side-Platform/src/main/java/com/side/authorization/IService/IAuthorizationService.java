/**
 * 
 */
package com.side.authorization.IService;

import java.util.List;

import com.side.authorization.dto.SideAuthorizationDto;
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
	
	/**
	 * 根据角色与菜单查询菜单权限
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<SideAuthorization> findAuthorizationByRoleAndMenu(SideAuthorizationDto dto) throws Exception;
	
	/**
	 * 保存所有的角色权限
	 * @see 判断authorization对象中的operationList属性，如果该属性为空，并且id不为空。将当作要删除权限。
	 * @param list
	 * @throws Exception
	 */
	public void saveAllAuthorizations(List<SideAuthorizationDto> list) throws Exception;

}
