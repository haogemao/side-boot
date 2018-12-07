///**
// * 
// */
//package com.side.zuul.config;
//
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
//import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
//import org.springframework.security.web.csrf.CsrfFilter;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.side.zuul.filters.SideOauth2ClientContextFilter;
//
///**
// * @author gmc
// *
// */
//@Configuration
//@EnableOAuth2Sso
//@EnableResourceServer
//@Order(value = 0)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//    private ResourceServerTokenServices resourceServerTokenServices;
//
//    @Bean
//    @Primary
//    public OAuth2ClientContextFilter dynamicOauth2ClientContextFilter() {
//        return new SideOauth2ClientContextFilter();
//    }
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/oauth/**", "/login").permitAll()
//		.anyRequest()
//		.authenticated().and().csrf()
//		.csrfTokenRepository(csrfTokenRepository()).and()
//		.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
//		.addFilterAfter(oAuth2AuthenticationProcessingFilter(), AbstractPreAuthenticatedProcessingFilter.class);
//	}
//	
//	private OAuth2AuthenticationProcessingFilter oAuth2AuthenticationProcessingFilter() {
//        OAuth2AuthenticationProcessingFilter oAuth2AuthenticationProcessingFilter =
//                new OAuth2AuthenticationProcessingFilter();
//        oAuth2AuthenticationProcessingFilter.setAuthenticationManager(oauthAuthenticationManager());
//        oAuth2AuthenticationProcessingFilter.setStateless(false);
//
//        return oAuth2AuthenticationProcessingFilter;
//    }
//	
//	private AuthenticationManager oauthAuthenticationManager() {
//        OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager();
//        oAuth2AuthenticationManager.setResourceId("apigateway");
//        oAuth2AuthenticationManager.setTokenServices(resourceServerTokenServices);
//        oAuth2AuthenticationManager.setClientDetailsService(null);
//
//        return oAuth2AuthenticationManager;
//    }
//	
//	private Filter csrfHeaderFilter() {
//		return new OncePerRequestFilter() {
//			@Override
//			protected void doFilterInternal(HttpServletRequest request,
//					HttpServletResponse response, FilterChain filterChain)
//					throws ServletException, IOException {
//				
//				CsrfToken csrf = (CsrfToken) request
//						.getAttribute(CsrfToken.class.getName());
//				if (csrf != null) {
////					Cookie cookie = new Cookie("XSRF-TOKEN",
////							csrf.getToken());
////					cookie.setPath("/");
////					response.addCookie(cookie);
//					response.addHeader("XSRF-TOKEN", csrf.getToken());
//				}
//				filterChain.doFilter(request, response);
//			}
//		};
//	}
//	
//	private CsrfTokenRepository csrfTokenRepository() {
//		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//		repository.setHeaderName("X-XSRF-TOKEN");
//		return repository;
//	}
//	
//}
