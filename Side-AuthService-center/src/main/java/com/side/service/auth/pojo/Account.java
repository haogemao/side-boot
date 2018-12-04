/**
 * 
 */
package com.side.service.auth.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author gmc
 * @see 帐户实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name="side_account", indexes= {@Index(columnList = "id"), 
		@Index(columnList = "accCode"), 
		@Index(columnList = "accName"),
		@Index(columnList = "accStatus")})
public class Account implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(length=32, nullable=false)
	private String accCode;
	
	@Column(length=100, nullable=false)
	private String accName;
	
	@Column(length=32, nullable=false)
	private String accPassword;
	
	@Column(length=20, nullable=true)
	private String tel;
	
	@Column(nullable=true)
	private Character accSex;
	
	@Column
	private Date accBirthday;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="userId", insertable=true, nullable=true)
	private AdminUser userId;
	
	@Column(nullable=false)
	private Date createDate;
	
	@Column(nullable=false)
	private Integer createBy;
	
	@Column(nullable=true)
	private Date lastUpdateDate;
	
	@Column(length=32, nullable=true)
	private String lastUpdateBy;
	
	@Column(nullable=false)
	private Integer accStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccPassword() {
		return accPassword;
	}

	public void setAccPassword(String accPassword) {
		this.accPassword = accPassword;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Character getAccSex() {
		return accSex;
	}

	public void setAccSex(Character accSex) {
		this.accSex = accSex;
	}

	public Date getAccBirthday() {
		return accBirthday;
	}

	public void setAccBirthday(Date accBirthday) {
		this.accBirthday = accBirthday;
	}

	public AdminUser getUserId() {
		return userId;
	}

	public void setUserId(AdminUser userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Integer getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(Integer accStatus) {
		this.accStatus = accStatus;
	}
	
}
