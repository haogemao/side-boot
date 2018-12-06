/**
 * 
 */
package com.side.service.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gmc
 * @see 返回用户信息
 */
@RestController
public class UserInfoController {

	@GetMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
