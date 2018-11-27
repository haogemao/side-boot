package com.side.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class SideZuulApplication {

	public static void main(String[] args) {
		System.out.println("===============开始启动路由===============");
		SpringApplication.run(SideZuulApplication.class, args);
		System.out.println("===============路由启动完成===============");
	}
}
 