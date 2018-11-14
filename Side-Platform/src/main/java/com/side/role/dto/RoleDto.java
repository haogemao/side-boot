/**
 * 
 */
package com.side.role.dto;

import java.io.Serializable;

/**
 * @author gmc
 * @see 数据查询对象
 */
@SuppressWarnings("serial")
public class RoleDto implements Serializable {

	private Integer roleId;
	
	private String roleCode;
	
	private String roleName;
	
	private Integer roleStatus;
	
	private String key;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
