/**
 * 
 */
package com.side.service.auth.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author gmc
 *
 */
@Configurable
public class WebConfig extends WebMvcConfigurationSupport{

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("index/login");
        super.addViewControllers(registry);
    } 

}
