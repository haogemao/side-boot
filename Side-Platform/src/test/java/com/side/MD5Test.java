/**
 * 
 */
package com.side;

import java.util.ArrayList;
import java.util.List;

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
		
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < 5; i++) {
			list.add(String.valueOf(i));
		}
		System.out.println(list.toString().replace("[", "").replace("]", ""));
	}

}
