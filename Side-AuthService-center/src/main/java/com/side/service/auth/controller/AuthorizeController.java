/**
 * 
 */
package com.side.service.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gmc
 *
 */

@Controller
public class AuthorizeController {

	@RequestMapping("/login")
	public String toLogin() {
		SecurityContextHolder.getContext().getAuthentication();
		System.out.println("进入授权中心登录界面");
		return "index/login";
	}
	
	@RequestMapping(value="dologin", name="dologin", path="dologin", method=RequestMethod.POST)
	@ResponseBody
	public String doLogin(String userCode, String password, HttpServletRequest request) {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(userDetails != null) {
			System.out.println("获取到用户权限");
		}
		
		return "token";
		
	}
	
}
