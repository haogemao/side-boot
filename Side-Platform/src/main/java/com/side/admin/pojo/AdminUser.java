/**
 * 
 */
package com.side.admin.pojo;

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

import org.hibernate.annotations.DynamicUpdate;

/**
 * @author gmc
 *
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name="side_admin_user")
public class AdminUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long adminId;
	
	@Column(length=32, nullable=false)
	private String adminCode;
	
	@Column(length=100, nullable=false)
	private String adminName;
	
	@Column
	private Date createDate;
	
	@Column
	private Date lastUpdateDate;
	
	@Column(length=32, nullable=false)
	private Integer createBy;
	
	@Column(length=32, nullable=true)
	private Integer lastUpdateBy;
	
	@Column(nullable=false)
	private Integer adminStatus;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="account", insertable=true, nullable=true)
	private Account account;

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

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

	public Integer getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
