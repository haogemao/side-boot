package com.side;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages="com.side.*")
public class SidePlatformApplication {

	public static void main(String[] args) {
		System.out.println("================启动工程=================");
		
		SpringApplication.run(SidePlatformApplication.class, args);
		
		System.out.println("================工程已启动=================");
	}
}
