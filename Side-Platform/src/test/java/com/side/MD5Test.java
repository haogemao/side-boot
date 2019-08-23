/**
 * 
 */
package com.side;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author gmc
 *
 */
public class MD5Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("1") + " length:" + encoder.encode("1").length());
	}

}
