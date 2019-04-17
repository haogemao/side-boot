/**
 * 
 */
package com.side.authorization.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.side.menus.pojo.SideMenus;
import com.side.role.pojo.SideRole;

/**
 * @author gmc
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="side_authorization")
@JsonIgnoreProperties(value={"roleId","menuId","hibernateLazyInitializer","handler","fieldHandler"})
public class SideAuthorization implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer authorizationId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "roleId")
	private SideRole roleId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "menuId")
	private SideMenus menuId;
	
	@Column(name="operations", length=100, nullable=true)
	private String operationList;

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

	public String getOperationList() {
		return operationList;
	}

	public void setOperationList(String operationList) {
		this.operationList = operationList;
	}
	
}
