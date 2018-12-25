package com.side.service.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages="com.side.*")
@EnableJpaRepositories("com.side.*.*")
public class SideAuthServiceCenterApplication {

	public static void main(String[] args) {
		System.out.println("===============启动授权中心===============");
		
		SpringApplication.run(SideAuthServiceCenterApplication.class, args);
		
		System.out.println("===============授权中心完成===============");
	}
}
