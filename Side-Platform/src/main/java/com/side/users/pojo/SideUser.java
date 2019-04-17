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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author gmc
 *
 */
//@Cache(region="user", include="all", usage=CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name="side_user")
public class SideUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long userId;
	
	@Column(length=32, nullable=false)
	private String userCode;
	
	@Column(length=100, nullable=false)
	private String userName;
	
	@Column
	private Date createDate;
	
	@Column
	private Date lastUpdateDate;
	
	@Column(length=32, nullable=false)
	private Integer createBy;
	
	@Column(length=32, nullable=true)
	private Integer lastUpdateBy;
	
	@Column(nullable=false)
	private Integer userStatus;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="account", insertable=true, nullable=true)
	private Account account;


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Integer lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

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
}
