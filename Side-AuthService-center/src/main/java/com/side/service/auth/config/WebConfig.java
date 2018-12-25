///**
// * 
// */
//package com.side.service.auth.config;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.Ordered;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.web.filter.ForwardedHeaderFilter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
////import com.side.service.auth.filter.AuthServerForwardedHeadFilter;
//
///**
// * @author gmc
// *
// */
//@Configurable
//@EnableWebSecurity
//public class WebConfig extends WebMvcConfigurationSupport{
//
//	@Bean
//    FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilter() {
//        FilterRegistrationBean<ForwardedHeaderFilter> filterRegBean = new FilterRegistrationBean<ForwardedHeaderFilter>();
//        filterRegBean.setFilter(new ForwardedHeaderFilter());
//        filterRegBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return filterRegBean;
//    }
//	
////	@Override
////    public void addViewControllers(ViewControllerRegistry registry) {
////		registry.addViewController("/login").setViewName("index/login");
////        registry.addViewController("/oauth/confirm_access").setViewName("index/authorize");
////    } 
////	
////	@Override
////	public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
////        super.addResourceHandlers(registry);
////    }
//
//}
