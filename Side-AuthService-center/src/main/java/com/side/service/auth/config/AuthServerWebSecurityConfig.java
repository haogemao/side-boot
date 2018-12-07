/**
 * 
 */
package com.side.service.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.side.service.auth.filter.MyOAuth2ClientAuthenticationProcessingFilter;
import com.side.service.auth.handler.MySavedRequestAwareAuthenticationSuccessHandler;
import com.side.service.auth.service.userDetailsService.MyDaoAuthenticationProvider;
import com.side.service.auth.service.userDetailsService.UserDetailsServiceImpl;


/**
 * @author gmc
 *
 */
@Configuration
@EnableWebSecurity
public class AuthServerWebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
//		.addFilterAfter(myOAuth2ClientAuthenticationProcessingFilter(), BasicAuthenticationFilter.class)
		.formLogin().loginPage("/login")
		.permitAll()
		.usernameParameter("userCode")
		.passwordParameter("password")
		.successHandler(new MySavedRequestAwareAuthenticationSuccessHandler())
		.and().headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		MyDaoAuthenticationProvider provider = new MyDaoAuthenticationProvider(userDetailsService, passwordEncoder());
		auth.authenticationProvider(provider);
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/css/**", "/js/**","/images/**", "/easyui1.4/**", "/media/**", "/fonts/**", "/webjars/**", "/common/**", "/style/**", "/callcenter/**");
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	public MyOAuth2ClientAuthenticationProcessingFilter myOAuth2ClientAuthenticationProcessingFilter() {
		MyOAuth2ClientAuthenticationProcessingFilter filter = new MyOAuth2ClientAuthenticationProcessingFilter("http://localhost:8801/side/doLogin");
		filter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            String authUrl = request.getParameter("redirect_url");
            System.out.println("当前redirect_url参数:" + authUrl);
            response.sendRedirect(authUrl);
        });
		return filter;
	}

	
}
