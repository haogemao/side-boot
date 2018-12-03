/**
 * 
 */
package com.side.restAPI.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/restMenuAPI")
public class RestMenusController {

	@Qualifier("sideMenuService")
	@Autowired
	private ISideMenuService sideMenuService;
	
	@RequestMapping(value="/menuEdit", method=RequestMethod.GET)
	public Map<String, Object> menuEdit(MenuDto dto) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<SideMenus> mode;
		try {
			dto.setCurrentUser(String.valueOf(1));
			sideMenuService.editMenuByDto(dto);
			
			System.out.println("dto data: " + JsonTools.obj2Json(dto));
			
			dto.setKey("");
			dto.setMenuCode("");
			dto.setMenuName("");
			dto.setMenuPath("");
			if(dto.getParentMenu() != null) {
				dto.setParentId(String.valueOf(dto.getParentMenu()));
			}
			
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
}
