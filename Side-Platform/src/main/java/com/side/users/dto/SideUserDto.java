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

	private Long userId;
	
	private String userCode;
	
	private String userName;
	
	private Integer userStatus;
	
	private Integer account;
	
	private String key;

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

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
