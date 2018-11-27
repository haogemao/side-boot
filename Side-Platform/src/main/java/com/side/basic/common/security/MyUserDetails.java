/**
 * 
 */
package com.side.basic.common.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;

import com.side.admin.pojo.AdminUser;

/**
 * @author gmc
 *
 */
public class MyUserDetails extends User {
	
	
	private AdminUser user;

	@SuppressWarnings("unchecked")
	public MyUserDetails(AdminUser user) {
		super(user.getAdminCode(), user.getAccount().getAccPassword(), true, true, true, true, Collections.EMPTY_SET);
	     this.user = user;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminUser getUser() {
		return user;
	}

	public void setUser(AdminUser user) {
		this.user = user;
	}

}
