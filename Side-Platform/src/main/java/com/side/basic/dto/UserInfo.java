/**
 * 
 */
package com.side.basic.dto;

import java.io.Serializable;

import com.side.authorization.pojo.SideAuthorization;
import com.side.role.pojo.SideRole;
import com.side.users.pojo.SideUser;

/**
 * @author gmc
 * @see 用户对象
 */
@SuppressWarnings("serial")
public class UserInfo implements Serializable {
	
	private SideUser user;
	
	private SideRole role;
	
	private SideAuthorization authorization;

	public SideUser getUser() {
		return user;
	}

	public void setUser(SideUser user) {
		this.user = user;
	}

	public SideRole getRole() {
		return role;
	}

	public void setRole(SideRole role) {
		this.role = role;
	}

	public SideAuthorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(SideAuthorization authorization) {
		this.authorization = authorization;
	}

}
