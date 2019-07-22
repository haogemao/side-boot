/**
 * 
 */
package com.side.service.auth.service.passwordEncoder;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.side.common.UtilRSA;

/**
 * @author gmc
 * 
 */
public class MyPasswordEncoder extends BCryptPasswordEncoder {

	//重写父类方法
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		try {
			String pw = UtilRSA.decryptByPrivateKey(
					Base64.decodeBase64(rawPassword.toString()), UtilRSA.THIS_PRIVATEKEY);
			return super.matches(pw,encodedPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
