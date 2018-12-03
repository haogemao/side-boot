/**
 * 
 */
//package com.side.basic.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//
///**
// * @author gmc
// *
// */
//@Configuration
//@EnableResourceServer
//public class AuthResourceServerConfig extends ResourceServerConfigurerAdapter{
//
//	@Bean
//    public RemoteTokenServices remoteTokenServices() {
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8810/auth-center/oauth/check_token");
//        return remoteTokenServices;
//    }
//	
//	 @Override
//	    public void configure(HttpSecurity http) throws Exception {
//	        http.authorizeRequests().antMatchers("/side/**").authenticated()
//	        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
//	    }
//
//	    @Override
//	    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//	        resources.resourceId("oauth2-resource");
//	    }
//	
//}
