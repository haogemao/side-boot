/**
 * 
 */
package com.side.service.auth.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * @author gmc
 * 
 */

@Configuration
public class DataSourceConfig {

	@Primary
	@Bean("dataSource")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSourceOne(){
	    return DruidDataSourceBuilder.create().build();
	}
	
}
