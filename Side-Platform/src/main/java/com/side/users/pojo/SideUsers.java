/**
 * 
 */
package com.side.users.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author gmc
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="side_users")
public class SideUsers implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer userId;
	
	@Column(length=50, nullable=false)
	private String userCode;
	
	@Column(length=100, nullable=false)
	private String userName;
	
	@Column
	private Integer level;
	
	@Column(length=100, nullable=true)
	private String ipAddress;
	
	@Column(nullable=false)
	private Integer createBy;
	
	@Column(nullable=false)
	private Date createDate;
	
	@Column(nullable=true)
	private Integer lastUpdateBy;
	
	@Column(nullable=true)
	private Date lastUpdateDate;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, targetEntity=SideUserAccount.class)
	@JoinColumn(name="accountId", insertable=true, nullable=true)
	private SideUserAccount accountId;
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Integer lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}
