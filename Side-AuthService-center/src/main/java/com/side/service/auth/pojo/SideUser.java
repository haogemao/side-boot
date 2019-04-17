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
	private Long userid;
	
	@Column(name="userCode", length=32, nullable=false)
	private String usercode;
	
	@Column(name="userName",length=100, nullable=false)
	private String username;
	
	@Column(name="createDate")
	private Date createdate;
	
	@Column(name="lastUpdateDate")
	private Date lastupdatedate;
	
	@Column(name="createBy", length=32, nullable=false)
	private Integer createby;
	
	@Column(name="lastUpdateBy", length=32, nullable=true)
	private Integer lastupdateby;
	
	@Column(name="userStatus", nullable=false)
	private Integer userstatus;
	
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

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(Integer userstatus) {
		this.userstatus = userstatus;
	}

}
