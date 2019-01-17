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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author gmc
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="side_user")
public class SideUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long userId;
	
	@Column(name="userCode", length=32, nullable=false)
	private String userCode;
	
	@Column(name="userName",length=100, nullable=false)
	private String userName;
	
	@Column(name="createDate")
	private Date createdate;
	
	@Column(name="lastUpdateDate")
	private Date lastupdatedate;
	
	@Column(name="createBy", length=32, nullable=false)
	private Integer createby;
	
	@Column(name="lastUpdateBy", length=32, nullable=true)
	private Integer lastupdateby;
	
	@Column(name="adminStatus", nullable=false)
	private Integer adminstatus;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="account", insertable=true, nullable=true)
	private Account account;


	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getLastupdatedate() {
		return lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public Integer getCreateby() {
		return createby;
	}

	public void setCreateby(Integer createby) {
		this.createby = createby;
	}

	public Integer getLastupdateby() {
		return lastupdateby;
	}

	public void setLastupdateby(Integer lastupdateby) {
		this.lastupdateby = lastupdateby;
	}

	public Integer getAdminstatus() {
		return adminstatus;
	}

	public void setAdminstatus(Integer adminstatus) {
		this.adminstatus = adminstatus;
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
}
