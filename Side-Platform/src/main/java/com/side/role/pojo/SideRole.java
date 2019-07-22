/**
 * 
 */
package com.side.role.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.side.authorization.pojo.SideAuthorization;

/**
 * @author gmc
 *
 */
@SuppressWarnings("serial")
@Entity
@DynamicUpdate
@Table(name="side_role")
public class SideRole implements Serializable {
	
	public SideRole() {}
	
	public SideRole(String roleCode, String roleName, Integer roleStatus, Date createDate, Integer createBy) {
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleStatus = roleStatus;
		this.createDate = createDate;
		this.createBy = createBy;
	}
	
	public SideRole(Integer roleId) {
		this.roleId = roleId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer roleId;
	
	@Column(length=32, nullable=false)
	private String roleCode;
	
	@Column(length=100, nullable=false)
	private String roleName;
	
	@Column(nullable=false)
	private Integer roleStatus;
	
	@Column
	private Date createDate;
	
	@Column(length=32, nullable=false)
	private Integer createBy;
	
	@Column
	private Date lastUpdateDate;
	
	@Column(length=32, nullable=true)
	private Integer lastUpdateBy;
	
	@JsonIgnore
	@OneToMany(mappedBy="roleId", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<SideAuthorization> authorization;

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

	public Integer getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Integer lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Set<SideAuthorization> getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Set<SideAuthorization> authorization) {
		this.authorization = authorization;
	}
	
	
}
