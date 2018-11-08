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
@Table(name="side_users_account")
public class SideUserAccount implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer accountId;
	
	@Column(length=50, nullable=false)
	private String accountCode;
	
	@Column(length=100, nullable=false)
	private String accountName;
	
	@Column(length=20, nullable=false)
	private String password;
	
	@Column(length=20, nullable=true)
	private String tel;
	
	@Column(length=200, nullable=true)
	private String address;
	
	@Column
	private Integer sex;
	
	@Column
	private Date birthday;
	
	@Column(nullable=false)
	private Integer createBy;
	
	@Column(nullable=false)
	private Date createDate;
	
	@Column(nullable=true)
	private Integer lastUpdateBy;
	
	@Column(nullable=true)
	private Date lastUpdateDate;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, targetEntity=SideUsers.class)
	@JoinColumn(name="userId", insertable=true, nullable=true)
	private SideUsers userId;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
