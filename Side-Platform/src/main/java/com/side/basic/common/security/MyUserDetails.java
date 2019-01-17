/**
 * 
 */
package com.side.basic.common.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;

import com.side.users.pojo.SideUser;

/**
 * @author gmc
 *
 */
public class MyUserDetails extends User {
	
	
	private SideUser user;

	@SuppressWarnings("unchecked")
	public MyUserDetails(SideUser user) {
		super(user.getUserCode(), user.getAccount().getAccPassword(), true, true, true, true, Collections.EMPTY_SET);
	     this.user = user;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SideUser getUser() {
		return user;
	}

	public void setUser(SideUser user) {
		this.user = user;
	}

}
