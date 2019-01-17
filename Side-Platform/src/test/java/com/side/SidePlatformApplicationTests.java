package com.side;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.side.authorization.IService.IAuthorizationService;
import com.side.authorization.IService.IUserRoleService;
import com.side.authorization.pojo.SideAuthorization;
import com.side.authorization.pojo.SideUserRole;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.basic.common.utils.PageMode;
import com.side.basic.common.utils.UtilMD5;
import com.side.basic.config.DataSourceConfig;
import com.side.basic.config.HibernateSessionConfig;
import com.side.menus.IService.ISideMenuService;
import com.side.menus.pojo.SideMenus;
import com.side.role.IRoleService.IRoleService;
import com.side.role.pojo.SideRole;
import com.side.users.IService.ISideAccountService;
import com.side.users.IService.ISideUserService;
import com.side.users.pojo.Account;
import com.side.users.pojo.SideUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, HibernateSessionConfig.class})
@SpringBootTest(classes = SidePlatformApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SidePlatformApplicationTests {

	@Autowired
	@Qualifier("sideAccountService")
	private ISideAccountService sideAccountService;
	
	@Autowired
	@Qualifier("sideUserService")
	private ISideUserService sideUserService;
	
	@Autowired
	@Qualifier("roleService")
	private IRoleService roleService;
	
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;
	
	@Autowired
	private ISideMenuService menuService;
	
	@Autowired
	@Qualifier("authorizationService")
	private IAuthorizationService authorizationService;
	
	@Test
	public void adminTestCase() {
		SideUser admin = sideUserService.findSideUserByCode("00001");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Account account = new Account();
		try {
			if(admin == null) {
				admin = new SideUser();
				admin.setUserCode("00001");
				admin.setUserName("系统测试员");
				admin.setUserStatus(1);
				admin.setCreateBy(1);
				admin.setCreateDate(new Date());
				admin.setAccount(account);
				
				account.setAccCode("000001");
				account.setAccName("测试人员");
				account.setAccStatus(1);
				account.setAccPassword(encoder.encode("123456"));
				account.setCreateBy(1);
				account.setCreateDate(new Date());
				account.setUserId(admin);
				
				sideUserService.save(admin);
			} else {
				admin.getAccount().setAccPassword(encoder.encode("123456"));
				System.out.println("密码:" + encoder.encode("123456"));
				sideUserService.update(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void roleTestCase() {
		SideRole role = roleService.findRoleByCode("0001");
		if(role == null) {
			role = new SideRole();
			role.setCreateBy(1);
			role.setCreateDate(new Date());
			role.setRoleCode("0001");
			role.setRoleName("平台管理员");
			role.setRoleStatus(1);
			roleService.save(role);
		}
	}
	
	@Test
	public void userRoleTestCase() {
		SideRole role = roleService.findRoleByCode("0001");
		SideUser admin = sideUserService.findSideUserByCode("00001");
		SideUserRole userRole = new SideUserRole();
		userRole.setRoleId(role);
		userRole.setUserId(admin);
		userRoleService.save(userRole);
	}
	
	@Test
	public void menuTestCase() {
		DetachedCriteriaTS<SideMenus> criteria = new DetachedCriteriaTS<SideMenus>(SideMenus.class);
		List<SideMenus> menuList = menuService.findAll(criteria);
		if(menuList == null || menuList.size() == 0) {
			SideMenus menu = new SideMenus();
			menu.setMenuCode("systemSetting");
			menu.setMenuName("系统设置");
			menu.setMenuSort(9999);
			menu.setMenuType(0);
			menu.setCreateBy(1);
			menu.setCreateDate(new Date());
			menuService.save(menu);
			
			SideMenus menu2 = new SideMenus();
			menu2.setMenuCode("custManager");
			menu2.setMenuName("客户管理");
			menu2.setMenuSort(1);
			menu2.setMenuType(0);
			menu2.setCreateBy(1);
			menu2.setCreateDate(new Date());
			menuService.save(menu2);
			
			SideMenus menu3 = new SideMenus();
			menu3.setMenuCode("userManager");
			menu3.setMenuName("用户管理");
			menu3.setMenuSort(2);
			menu3.setMenuType(0);
			menu3.setCreateBy(1);
			menu3.setCreateDate(new Date());
			menuService.save(menu3);
		}
	}
	
	@Test
	public void roleMenuTestCase() {
		SideRole role = roleService.findRoleByCode("0001");
		
		DetachedCriteriaTS<SideMenus> criteria = new DetachedCriteriaTS<SideMenus>(SideMenus.class);
		List<SideMenus> menuList = menuService.findAll(criteria);
		
		if(role != null && menuList != null && menuList.size() > 0) {
			for(SideMenus menu : menuList) {
				SideAuthorization authorization = new SideAuthorization();
				authorization.setMenuId(menu);
				authorization.setRoleId(role);
				authorizationService.save(authorization);
			}
		}
	}
	
	@Test
	public void userAuthorizationTestCase() {
		List<SideAuthorization> authorizations = authorizationService.findAuthorizationByRole("0001");
		if(authorizations != null && authorizations.size() > 0) {
			for(SideAuthorization authorization : authorizations) {
				System.out.println("authorization id:"+authorization.getAuthorizationId());
				System.out.println("authorization menuId:"+authorization.getMenuId().getMenuName());
				System.out.println("authorization roleId:"+authorization.getRoleId().getRoleName());
			}
		}
	}
	
	@Test
	public void menuTestCase1() {
		SideMenus parentMenu = menuService.get(SideMenus.class, 3);
		if(parentMenu != null) {
			SideMenus childMenus = new SideMenus();
			childMenus.setMenuCode("menuManager");
			childMenus.setMenuName("菜单管理2");
			childMenus.setMenuSort(5);
			childMenus.setParentMenu(parentMenu);
			childMenus.setMenuPath("/menu/manager");
			childMenus.setMenuType(0);
			childMenus.setCreateBy(1);
			childMenus.setCreateDate(new Date());
			menuService.update(childMenus);
		}
	}
	
	@Test
	public void menuDelCase2() {
		SideMenus parentMenu = menuService.get(SideMenus.class, 3);
		DetachedCriteriaTS<SideMenus> detachedCriteria = new DetachedCriteriaTS<SideMenus>(SideMenus.class);
		detachedCriteria.add(Restrictions.eq("parentMenu", parentMenu));
		menuService.deleteAll(menuService.findAll(detachedCriteria));
	}
	
	@Test
	public void sqlTestCase() {
		String sql = "select * from side_menus where menuCode=?";
		Map<String, String> params = new HashMap<String, String>();
		int pageNumber = 1;
		int pageSize = 10;
		params.put("menuCode", "userManager");
		PageMode pageMode = menuService.findBySQL(sql, params, pageNumber, pageSize);
		if(pageMode != null) {
			System.out.println("pageMode.records size : " + pageMode.getRecords().size());
		}
	}
	
	@Test
	public void sqlTestCase2() {
		String sql = "select * from side_menus where menuCode=?";
		Map<String, String> params = new HashMap<String, String>();
		int pageNumber = 1;
		int pageSize = 10;
		params.put("menuCode", "userManager");
		PageMode pageMode = menuService.findBySQL(sql, params, pageNumber, pageSize, SideMenus.class);
		if(pageMode != null) {
			System.out.println("pageMode.records size : " + pageMode.getRecords().size());
		}
	}
	
	
	@Test
	public void sqlObjTestCase() {
		String sql = "select * from side_menus where menuCode=?";
		Map<String, String> params = new HashMap<String, String>();
		params.put("menuCode", "userManager");
		Object sideMenu = menuService.findObjBySQL(sql, params);
		if(sideMenu != null) {
			System.out.println("sideMenu.menuCode : ");
		}
	}
	
	@Test
	public void sqlObjTestCase2() {
		String sql = "update side_menus set remark=? where id=?";
		Map<String, String> params = new HashMap<String, String>();
		params.put("remark", "123456");
		params.put("id", "1");
		menuService.executeObjBySql(sql, params);
	}
	
	@Test
	public void findPageTest() {
		DetachedCriteriaTS<SideMenus> criteria = new DetachedCriteriaTS<SideMenus>(SideMenus.class);
		PageMode<SideMenus> list = menuService.findForList(criteria, 1, 2);
		if(list != null) {
			System.out.println("总页数："+list.getPageCount());
		}
	}
	
	@Test
	public void findAuthorizationByRoleCode() {
		List<SideAuthorization> list = authorizationService.findParentAuthorizationByRole("0001");
		if(!list.isEmpty()) {
			for (SideAuthorization sideAuthorization : list) {
				System.out.println("menu:" + sideAuthorization.getMenuId().getMenuName());
			}
		}
	}
}
