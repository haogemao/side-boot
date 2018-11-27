package com.side;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages="com.side.*")
public class SidePlatformApplication {

	public static void main(String[] args) {
		System.out.println("================project startup=================");
		
		SpringApplication.run(SidePlatformApplication.class, args);
		
		System.out.println("================project startup over=================");
	}
}
