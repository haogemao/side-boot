/**
 * 
 */
package com.side.basic.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.side.basic.common.security.SideDaoAuthenticationProvider;
import com.side.basic.common.security.SideUserServiceDetails;

/**
 * @author gmc
 * @see spring security个性化配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("sideUserDetailService")
	private SideUserServiceDetails sideUserServiceDetails;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http
//        .authorizeRequests().anyRequest().authenticated()
//        .and().formLogin()
//        .and().csrf().disable().httpBasic();
		http.csrf().disable()
		.authorizeRequests().antMatchers("/restLoginAPI/**", "/restMenuAPI/**").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin().loginPage("/login")
		.successForwardUrl("/dologin")
		.failureUrl("/loginFail")
		.permitAll()
		.usernameParameter("userCode")
		.passwordParameter("password")
		.and().headers().frameOptions().disable()
		.and().logout().permitAll();
		
	}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//自定义用户验证，根据usercode+password方式进行验证
		SideDaoAuthenticationProvider cesDaoAuthenticationProvider = new SideDaoAuthenticationProvider(sideUserServiceDetails, passwordEncoder());
		auth.authenticationProvider(cesDaoAuthenticationProvider);
	}
	 @Override
     public void configure(WebSecurity web) throws Exception {
		 web.ignoring().antMatchers("/css/**", "/js/**","/images/**", "/easyui1.4/**", "/media/**", "/fonts/**", "/webjars/**", "/common/**", "/style/**", "/callcenter/**");
     }
	 
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
//	@Override
//    public void configure(AuthenticationManagerBuilder auth) {
//		SideDaoAuthenticationProvider cesDaoAuthenticationProvider = new SideDaoAuthenticationProvider(sideUserServiceDetails, 
//				new PasswordEncoder() {
//
//					@Override
//					public String encode(CharSequence rawPassword) {
//						return UtilMD5.MD5((String)rawPassword);
//					}
//		
//					@Override
//					public boolean matches(CharSequence rawPassword, String encodedPassword) {
//						return encodedPassword.equals(UtilMD5.MD5((String)rawPassword));
//					}
//					
//				});
//        auth.authenticationProvider(cesDaoAuthenticationProvider);
//    }
//	
//	@Bean("authenticationManager")
//	public AuthenticationManager authenticationManager() {
//		try {
//			return this.authenticationManagerBean();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
	
}
