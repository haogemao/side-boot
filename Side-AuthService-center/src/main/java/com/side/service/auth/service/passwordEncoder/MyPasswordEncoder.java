/**
 * 
 */
package com.side.service.auth.service.passwordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.side.service.auth.common.UtilMD5;

/**
 * @author gmc
 * 
 */
public class MyPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return UtilMD5.MD5((String)rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(UtilMD5.MD5((String)rawPassword));
	}

}
