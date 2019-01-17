/**
 * 
 */
package com.side.service.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.side.service.auth.pojo.SideUser;

/**
 * @author gmc
 * @see 用户数据操作接口
 */
public interface ISideUserRepository extends JpaRepository<SideUser, Integer> {

	/**
	 * 根据adminCode查找用户信息
	 * @param adminCode
	 * @return
	 */
	@Query(value="select a.id, a.userCode, a.userName, a.userStatus, a.createBy, a.createDate, a.lastUpdateBy, a.lastUpdateDate, a.account from side_user a where a.userCode=?1", nativeQuery=true)
	public SideUser findByUsercode(String usercode);
}
