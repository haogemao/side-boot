/**
 * 
 */
package com.side.service.auth.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.side.service.auth.pojo.AdminUser;
import com.side.service.auth.pojo.SideUserRole;

/**
 * @author gmc
 *
 */
public interface IUserRoleRepository extends JpaRepository<SideUserRole, Integer> {

	public List<SideUserRole> findByUserId(AdminUser userId);
}
