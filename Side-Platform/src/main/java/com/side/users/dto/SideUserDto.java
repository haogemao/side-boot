/**
 * 
 */
package com.side.users.dto;

import java.io.Serializable;

/**
 * @author gmc
 * @see 用户对象参数，用于接收前端参数
 */
@SuppressWarnings("serial")
public class SideUserDto implements Serializable {
	
	public SideUserDto() {
	}

	public SideUserDto(Long userId, String userCode, String userName, Integer userStatus, Long account,
			Long roleId, String roleName) {
		this.userId = userId;
		this.userCode = userCode;
		this.userName = userName;
		this.userStatus = userStatus;
		this.account = account;
		this.roleId = roleId;
		this.roleName = roleName;
	}

	private Long userId;
	
	private String userCode;
	
	private String userName;
	
	private Integer userStatus;
	
	private Long account;
	
	private Long roleId;
	
	private String roleName;
	
	private String searchKey;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
