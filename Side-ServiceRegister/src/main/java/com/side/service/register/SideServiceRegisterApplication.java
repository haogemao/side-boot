package com.side.service.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SideServiceRegisterApplication {

	public static void main(String[] args) {
		System.out.println("=================== 启动服务注册中心 ====================");
		SpringApplication.run(SideServiceRegisterApplication.class, args);
		System.out.println("=================== 服务注册中心启动完成 ====================");
	}
}
