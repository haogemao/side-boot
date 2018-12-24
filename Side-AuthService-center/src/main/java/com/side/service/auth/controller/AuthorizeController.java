/**
 * 
 */
package com.side.service.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gmc
 *
 */

@Controller
@RequestMapping("/")
public class AuthorizeController {

	@RequestMapping("/login")
	public String toLogin() {
		System.out.println("进入授权中心登录界面");
		return "index/login";
	}
	
	@RequestMapping("/oauth/confirm_access")
	public String confirm() {
		return "index/authorize";
	}
}
