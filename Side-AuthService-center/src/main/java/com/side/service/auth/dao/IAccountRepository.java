/**
 * 
 */
package com.side.service.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.side.service.auth.pojo.Account;
import com.side.service.auth.pojo.SideUser;

/**
 * @author gmc
 *
 */
public interface IAccountRepository extends JpaRepository<Account, Integer> {

	public Account findByUserId(SideUser userId);
}
