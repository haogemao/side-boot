/**
 * 
 */
package com.side.basic.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * @author gmc
 * @see 数据源配置加载类
 */
@Configuration
public class DataSourceConfig {

//	@Autowired
//    private Environment env;
	
//	@Bean(name="comboPooledDataSource")
//    public ComboPooledDataSource dataSource() throws PropertyVetoException {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        dataSource.setDriverClass(env.getProperty("spring.datasource.driver-class-name"));
//        dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
//        dataSource.setUser(env.getProperty("spring.datasource.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource.password"));
//        dataSource.setMaxPoolSize(500);
//        dataSource.setMinPoolSize(5);
//        dataSource.setInitialPoolSize(10);
//        dataSource.setMaxIdleTime(300);
//        dataSource.setAcquireIncrement(5);
//        dataSource.setIdleConnectionTestPeriod(60);
//        return dataSource;
//    }
	
	/**
	 * 默认数据源.
	 * @return
	 */
//	@Bean("dataSource")
//	@Primary
//	@ConfigurationProperties(prefix = "spring.datasource.first.hikari")
//	public HikariDataSource dataSource() {
//		return DataSourceBuilder.create().type(HikariDataSource.class).build();
//	}
//	
//	/**
//	 * 其他数据源.
//	 * @return
//	 */
//	@Bean("secondDataSource")
//	@ConfigurationProperties(prefix = "spring.datasource.second.hikari")
//	public HikariDataSource secondDataSource() {
//		return DataSourceBuilder.create().type(HikariDataSource.class).build();
//	}
	
	@Primary
	@Bean("dataSource")
	@ConfigurationProperties("spring.datasource.druid.one")
	public DataSource dataSourceOne(){
	    return DruidDataSourceBuilder.create().build();
	}
	
}
