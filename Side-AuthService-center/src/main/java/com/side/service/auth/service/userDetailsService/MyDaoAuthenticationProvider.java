/**
 * 
 */
package com.side.service.auth.service.userDetailsService;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author gmc
 *
 */
public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

	public MyDaoAuthenticationProvider() {}
	
	public MyDaoAuthenticationProvider(UserDetailsServiceImpl userServiceDetails, PasswordEncoder passwordEncode) {
		setUserDetailsService(userServiceDetails);
		setPasswordEncoder(passwordEncode);
	}
}
