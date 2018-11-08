/**
 * 
 */
package com.side.menus.IService;

import java.util.List;

import com.side.basic.IbaseService.ISideBasicService;
import com.side.basic.SideException.SideCustException;
import com.side.basic.common.utils.PageMode;
import com.side.menus.dto.MenuDto;
import com.side.menus.pojo.SideMenus;

/**
 * @author gmc
 * @see 提供功能菜单查询方法
 */
public interface ISideMenuService extends ISideBasicService<SideMenus> {
	
	/**
	 * 获取所有父级菜单
	 * @return
	 */
	public List<SideMenus> getParents();
	
	/**
	 * 根据父级菜单获取所有子级菜单
	 * @param parentId
	 * @return
	 */
	public List<SideMenus> getChildByParentId(Integer parentId);
	
	/**
	 * 根据条件查询菜单
	 * @param dto
	 * @return
	 */
	public List<SideMenus> findMenuByKey(MenuDto dto) throws SideCustException;
	
	/**
	 * 根据dto对象操作菜单
	 * @param dto
	 */
	public void editMenuByDto(MenuDto dto) throws SideCustException;
	
	/**
	 * 根据dto删除菜单对象
	 * @param dto
	 * @throws SideCustException
	 */
	public void delMenuByDto(MenuDto dto) throws SideCustException;

}
