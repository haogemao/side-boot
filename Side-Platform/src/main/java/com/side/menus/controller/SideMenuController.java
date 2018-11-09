/**
 * 
 */
package com.side.menus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.side.admin.pojo.AdminUser;
import com.side.basic.SideException.SideCustException;
import com.side.basic.common.josn.JsonTools;
import com.side.menus.IService.ISideMenuService;
import com.side.menus.dto.MenuDto;
import com.side.menus.pojo.SideMenus;

/**
 * @author gmc
 *
 */

@Controller
@RequestMapping("/menu")
public class SideMenuController {
	
	@Qualifier("sideMenuService")
	@Autowired
	private ISideMenuService sideMenuService;
	
	@RequestMapping("/manager")
	public String toList(ModelMap modelMap) {
		List<SideMenus> parentMenus = sideMenuService.getParents();
		modelMap.put("parents", parentMenus);
		return "/system/sys_menus";
	}
	
	@RequestMapping("search")
	@ResponseBody
	public Map<String, Object> findMenus(MenuDto dto){
		Map<String, Object> result = new HashMap<String, Object>();
		List<SideMenus> mode;
		try {
			mode = sideMenuService.findMenuByKey(dto);
			result.put("success", true);
			result.put("data", JsonTools.obj2Json(mode));
			return result;
		} catch (SideCustException e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("data", null);
			return result;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("data", null);
			return result;
		}
		
	}
	
	@RequestMapping("editMenuService")
	@ResponseBody
	public Map<String, Object> menuEdit(MenuDto dto, HttpSession session){
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<SideMenus> mode;
		try {
			AdminUser user = (AdminUser) session.getAttribute("user_info");
			if(user != null) {
				dto.setCurrentUser(String.valueOf(user.getAdminId()));
			}
			sideMenuService.editMenuByDto(dto);
			
			dto.setKey("");
			dto.setMenuCode("");
			dto.setMenuName("");
			dto.setMenuPath("");
			dto.setParentId(String.valueOf(dto.getParentMenu()));
			
			mode = sideMenuService.findMenuByKey(dto);
			result.put("success", true);
			result.put("data", JsonTools.obj2Json(mode));
			return result;
			
		} catch (SideCustException e) {
			result.put("success", false);
			result.put("data", null);
			return result;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("data", null);
			return result;
		}
	}
	
	@RequestMapping("delMenuService")
	@ResponseBody
	public Map<String, String> menuDelete(MenuDto dto){
		
		return null;
	}

}
