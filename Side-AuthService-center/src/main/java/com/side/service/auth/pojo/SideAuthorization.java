/**
 * 
 */
package com.side.service.auth.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author gmc
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="side_authorization")
public class SideAuthorization implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer authorizationId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "roleId")
	private SideRole roleId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "menuId")
	private SideMenus menuId;

	public Integer getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(Integer authorizationId) {
		this.authorizationId = authorizationId;
	}

	public SideRole getRoleId() {
		return roleId;
	}

	public void setRoleId(SideRole roleId) {
		this.roleId = roleId;
	}

	public SideMenus getMenuId() {
		return menuId;
	}

	public void setMenuId(SideMenus menuId) {
		this.menuId = menuId;
	}
	
}
