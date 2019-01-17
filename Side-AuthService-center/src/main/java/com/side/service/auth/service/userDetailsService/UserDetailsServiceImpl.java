package com.side.service.auth.service.userDetailsService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.side.service.auth.pojo.Account;
import com.side.service.auth.pojo.SideUser;
import com.side.service.auth.pojo.SideUserRole;
import com.side.service.auth.service.dataservice.IAccountService;
import com.side.service.auth.service.dataservice.IUserRoleService;
import com.side.service.auth.service.dataservice.IUserService;

/**
 * 自定义用户校验服务
 * @author gmc
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserRoleService userRoleService;
	
	@Autowired
	private IAccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		SideUser sideUser = userService.findByUserCode(username);
		List<SideUserRole> roles = userRoleService.findByUserId(sideUser);
		
		if(sideUser == null) {
			throw new UsernameNotFoundException("不存在该用户!	");
		}
		
		Account account = accountService.findByUserId(sideUser);
		
		for(SideUserRole role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleId().getRoleCode()));
		}
		
		return new org.springframework.security.core.userdetails.User(sideUser.getUserCode(),
				account.getAccPassword(), authorities);
	}

}
