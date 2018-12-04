/**
 * 
 */
package com.side.service.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.side.service.auth.pojo.AdminUser;

/**
 * @author gmc
 * @see 用户数据操作接口
 */
public interface IAdminUserRepository extends JpaRepository<AdminUser, Integer> {

	/**
	 * 根据adminCode查找用户信息
	 * @param adminCode
	 * @return
	 */
	@Query(value="select a.id, a.adminCode, a.adminName, a.adminStatus, a.createBy, a.createDate, a.lastUpdateBy, a.lastUpdateDate, a.account from side_admin_user a where a.admincode=?1", nativeQuery=true)
	public AdminUser findByAdmincode(String admincode);
}
