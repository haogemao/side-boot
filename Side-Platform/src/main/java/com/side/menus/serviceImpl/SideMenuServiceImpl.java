/**
 * 
 */
package com.side.menus.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.side.basic.SideException.SideCustException;
import com.side.basic.baseServiceImpl.SideBasicServiceImpl;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.menus.IDao.ISideMenuDao;
import com.side.menus.IService.ISideMenuService;
import com.side.menus.dto.MenuDto;
import com.side.menus.pojo.SideMenus;

/**
 * @author gmc
 */
@Service("sideMenuService")
public class SideMenuServiceImpl extends SideBasicServiceImpl<SideMenus> implements ISideMenuService {
	
	@Autowired
	@Qualifier("sideMenuDao")
	private ISideMenuDao sideMenuDao;

	@Override
	public List<SideMenus> getParents() {
		List<SideMenus> menus = new ArrayList<SideMenus>();
		DetachedCriteriaTS<SideMenus> criteria = new DetachedCriteriaTS<SideMenus>(SideMenus.class);
		criteria.add(Restrictions.eq("isParent", 0));
		menus = sideMenuDao.findAll(criteria);
		return menus;
	}

	@Override
	public List<SideMenus> getChildByParentId(Integer parentId) {
		List<SideMenus> menus = new ArrayList<SideMenus>();
		SideMenus parentMenu = sideMenuDao.get(SideMenus.class, parentId);
		DetachedCriteriaTS<SideMenus> criteria = new DetachedCriteriaTS<SideMenus>(SideMenus.class);
		criteria.add(Restrictions.eq("parentMenu", parentMenu));
		menus = sideMenuDao.findAll(criteria);
		return menus;
	}

	@Override
	public List<SideMenus> findMenuByKey(MenuDto dto) {
		List<SideMenus> pageModel = null;
		DetachedCriteriaTS<SideMenus> criteria = new DetachedCriteriaTS<SideMenus>(SideMenus.class);
		if(dto != null) {
			if (StringUtils.isNotEmpty(dto.getMenuCode())) {
				criteria.add(Restrictions.ilike("menuCode", dto.getMenuCode(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotEmpty(dto.getMenuName())) {
				criteria.add(Restrictions.ilike("menuName", dto.getMenuName(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotEmpty(dto.getMenuPath())) {
				criteria.add(Restrictions.ilike("menuPath", dto.getMenuPath(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotEmpty(dto.getParentId())) {
				SideMenus parent = new SideMenus();
				parent.setMenuId(Integer.valueOf(dto.getParentId()));
				criteria.add(Restrictions.eq("parentMenu", parent));
			}
			if (StringUtils.isNotEmpty(dto.getKey())) {
				criteria.add(Restrictions.or(Restrictions.ilike("menuCode", dto.getKey(), MatchMode.ANYWHERE), 
							Restrictions.ilike("menuName", dto.getKey(), MatchMode.ANYWHERE), 
							Restrictions.ilike("menuPath", dto.getKey(), MatchMode.ANYWHERE)));
			}
			if (dto.getIsParent() != null) {
				criteria.add(Restrictions.eq("isParent", dto.getIsParent()));
			}
		}
		pageModel = sideMenuDao.findAll(criteria);
		return pageModel;
	}

	@Override
	@Transactional
	public void editMenuByDto(MenuDto dto) throws SideCustException {
		
		SideMenus menu = null;
		
		if(dto != null) {
			if(StringUtils.isNotEmpty(dto.getMenuId()) && !"0".equals(dto.getMenuId())) {
				menu = sideMenuDao.get(SideMenus.class, Integer.parseInt(dto.getMenuId()));
				menu.setMenuName(dto.getMenuName());
				menu.setMenuPath(dto.getMenuPath());
				menu.setMenuCode(dto.getMenuCode());
				menu.setMenuSort(dto.getMenuSort());
				menu.setMenuType(Integer.parseInt(dto.getMenuType()));
				menu.setLastUpdateBy(Integer.parseInt(dto.getCurrentUser()));
				menu.setLastUpdateDate(dto.getDate());
				if(StringUtils.isNotEmpty(dto.getIcon())) {
					menu.setIcon(dto.getIcon());
				}
			} else {
				menu = new SideMenus();
				menu.setMenuName(dto.getMenuName());
				menu.setMenuPath(dto.getMenuPath());
				menu.setMenuCode(dto.getMenuCode());
				menu.setMenuSort(dto.getMenuSort());
				menu.setMenuType(Integer.parseInt(dto.getMenuType()));
				menu.setLastUpdateBy(Integer.parseInt(dto.getCurrentUser()));
				menu.setLastUpdateDate(dto.getDate());
				menu.setCreateBy(Integer.parseInt(dto.getCurrentUser()));
				menu.setCreateDate(dto.getDate());
				menu.setIsParent(dto.getIsParent());
				if(dto.getParentMenu() != null) {
					SideMenus parent = sideMenuDao.get(SideMenus.class, dto.getParentMenu());
					if(parent != null) {
						menu.setParentMenu(parent);
					}
				}
				
			}
		}
		
		sideMenuDao.saveOrUpdate(menu);
	}

	@Override
	@Transactional
	public void delMenuByDto(MenuDto dto) throws SideCustException {
		if(StringUtils.isNotBlank(dto.getMenuId())) {
			SideMenus menu = sideMenuDao.get(SideMenus.class, Integer.valueOf(dto.getMenuCode()));
			if(menu != null) {
				sideMenuDao.delete(menu);
			} else {
				throw new SideCustException("没有此菜单");
			}
		} else {
			throw new SideCustException("参数有误");
		}
	}
	
}
