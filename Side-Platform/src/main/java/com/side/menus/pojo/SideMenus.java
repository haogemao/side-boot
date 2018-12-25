/**
 * 
 */
package com.side.menus.pojo;

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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author gmc
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="side_menus", indexes= {@Index(columnList="id"),@Index(columnList="menuCode")})
public class SideMenus implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer menuId; //菜单id
	
	@Column(length=32, nullable=false)
	private String menuCode; //菜单编码
	
	@Column(length=100, nullable=false)
	private String menuName; //菜单名称
	
	@Column(length=200, nullable=true)
	private String menuPath; //菜单路径
	
	@Column(nullable=false)
	private Integer menuType; //菜单类型 0-菜单，1-按钮
	
	@Column
	private Integer menuSort; //菜单顺序
	
	@Column(length=500, nullable=true)
	private String remark; //菜单备注
	
	@Column(length=32, nullable=true)
	private String icon; //菜单图标
	
	@Column(nullable=false)
	private Integer createBy; //创建人
	
	@Column(nullable=false)
	private Date createDate; //创建日期
	
	@Column
	private Integer lastUpdateBy; //修改人
	
	@Column
	private Date lastUpdateDate; //修改日期
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "parentMenu")
	private SideMenus parentMenu; //父级菜单
	
	@JsonIgnore
	@OneToMany(mappedBy = "parentMenu", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
	private Set<SideMenus> childMenus; //子菜单
	
	@Column
	private Integer isParent; //是否父级
	

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public SideMenus getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(SideMenus parentMenu) {
		this.parentMenu = parentMenu;
	}

	public Set<SideMenus> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(Set<SideMenus> childMenus) {
		this.childMenus = childMenus;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}
}
