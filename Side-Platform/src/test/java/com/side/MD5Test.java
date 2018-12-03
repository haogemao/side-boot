/**
 * 
 */
package com.side;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.side.basic.common.utils.UtilMD5;

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
		
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		
		System.out.println("加密后:" + encode.encode("123456"));
	}

}
