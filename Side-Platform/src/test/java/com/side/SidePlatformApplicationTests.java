package com.side;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.jdbc.StringUtils;
import com.side.admin.IService.ISideAccountService;
import com.side.admin.IService.ISideAdminUserService;
import com.side.admin.pojo.Account;
import com.side.admin.pojo.AdminUser;
import com.side.authorization.IService.IAuthorizationService;
import com.side.authorization.IService.IUserRoleService;
import com.side.authorization.pojo.SideAuthorization;
import com.side.authorization.pojo.SideUserRole;
import com.side.basic.common.utils.DetachedCriteriaTS;
import com.side.basic.common.utils.UtilMD5;
import com.side.basic.config.DataSourceConfig;
import com.side.basic.config.HibernateSessionConfig;
import com.side.menus.IService.ISideMenuService;
import com.side.menus.pojo.SideMenus;
import com.side.role.IRoleService.IRoleService;
import com.side.role.pojo.SideRole;
import com.side.users.IUserService.ISideUserService;
import com.side.users.pojo.SideUsers;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, HibernateSessionConfig.class})
@SpringBootTest(classes = SidePlatformApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SidePlatformApplicationTests {

	@Autowired
	@Qualifier("sideUserService")
	private ISideUserService sideUserService;
	
	@Autowired
	@Qualifier("sideAccountService")
	private ISideAccountService sideAccountService;
	
	@Autowired
	@Qualifier("sideAdminUserService")
	private ISideAdminUserService sideAdminUserService;
	
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
	public void userTestCase() {
		SideUsers user = null;
		user = sideUserService.findUserByCode("000001");
		if(user == null) {
			user = new SideUsers();
			user.setUserCode("000001");
			user.setUserName("高铭潮");
			user.setLevel(1);
			user.setCreateBy(1);
			user.setCreateDate(new Date());
			sideUserService.save(user);
		}
	}
	
	@Test
	public void adminTestCase() {
		AdminUser admin = sideAdminUserService.findAdminUserByAdminCode("000001");
		Account account = new Account();
		try {
			if(admin == null) {
				admin = new AdminUser();
				admin.setAdminCode("00001");
				admin.setAdminName("系统管理员");
				admin.setAdminStatus(1);
				admin.setCreateBy(1);
				admin.setCreateDate(new Date());
				admin.setAccount(account);
				
				account.setAccCode("000001");
				account.setAccName("高铭潮");
				account.setAccStatus(1);
				account.setAccPassword(UtilMD5.MD5("Angel_870223"));
				account.setCreateBy(1);
				account.setCreateDate(new Date());
				account.setUserId(admin);
				
				sideAdminUserService.save(admin);
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
		AdminUser admin = sideAdminUserService.findAdminUserByAdminCode("00001");
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
		SideMenus parentMenu = menuService.get(SideMenus.class, 1);
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
}
