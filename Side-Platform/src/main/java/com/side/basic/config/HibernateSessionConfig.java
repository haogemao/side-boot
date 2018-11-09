/**
 * 
 */
package com.side.basic.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author gmc
 *
 */

@Configuration
@EnableJpaRepositories("com.side.*.*")
@EnableTransactionManagement
@EnableAutoConfiguration
public class HibernateSessionConfig {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private Environment env;
	
	
	@Bean("entityManagerFactory")
	@Primary
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        //此处com.example.*.model是你的java bean所在的包名
        factory.setPackagesToScan("com.side.*.pojo");
        factory.setDataSource(dataSource);

        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("spring.jpa.database", env.getProperty("spring.jpa.database"));
        jpaProperties.put("spring.jpa.open-in-view", env.getProperty("spring.jpa.open-in-view"));
        jpaProperties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        jpaProperties.put("hibernate.current_session_context_class", env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        
        /*----------------hibernate-redis二级缓存配置（单机模式） start-----------------*/
        jpaProperties.put("hibernate.cache.use_second_level_cache", env.getProperty("spring.jpa.properties.hibernate.cache.use_second_level_cache"));
        jpaProperties.put("hibernate.cache.use_query_cache", env.getProperty("spring.jpa.properties.hibernate.cache.use_query_cache"));
        jpaProperties.put("hibernate.cache.region.factory_class", env.getProperty("spring.jpa.properties.hibernate.cache.region.factory_class"));
        jpaProperties.put("hibernate.cache.provider_configuration_file_resource_path", env.getProperty("spring.jpa.properties.redisson-config"));
        jpaProperties.put("hibernate.generate_statistics", env.getProperty("spring.jpa.properties.hibernate.generate_statistics"));
        jpaProperties.put("hibernate.cache.region_prefix", env.getProperty("spring.jpa.properties.hibernate.cache.region_prefix"));
        jpaProperties.put("hibernate.cache.use_structured_entries", env.getProperty("spring.jpa.properties.hibernate.cache.use_structured_entries"));
        /*----------------hibernate-redis二级缓存配置（单机模式） end-----------------*/
        
        factory.setJpaPropertyMap(jpaProperties);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

//    @Bean
//    @Autowired
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory());
//        return txManager;
//    }
    
	/**
	 * hibernate事务管理
	 * @param sessionFactory
	 * @return
	 */
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
    	HibernateTransactionManager txManager = new HibernateTransactionManager();
    	txManager.setDataSource(dataSource);
    	txManager.setSessionFactory(sessionFactory);
		return txManager;
    }
    
    /**
     * hibernate sessionFactory bean
     * @param emf
     * @return
     */
    @Bean("sessionFactory")
    public SessionFactory sessionFactory(@Qualifier("entityManagerFactory") EntityManagerFactory emf){
    	SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        return sessionFactory;
    }
    
    /**
     * hibernate延迟加载
     * @param sessionFactory
     * @return
     */
    @Bean
    public FilterRegistrationBean<OpenSessionInViewFilter> registerOpenEntityManagerInViewFilterBean() {
        FilterRegistrationBean<OpenSessionInViewFilter> registrationBean = new FilterRegistrationBean<OpenSessionInViewFilter>();
        OpenSessionInViewFilter filter = new OpenSessionInViewFilter();
        filter.setSessionFactoryBeanName("sessionFactory");
        registrationBean.setFilter(filter);
        registrationBean.setOrder(5);
        return registrationBean;
    }
    
//    @Bean
//    public OpenSessionInViewInterceptor openSessionInViewInterceptor(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
//        OpenSessionInViewInterceptor interceptor = new OpenSessionInViewInterceptor();
//        interceptor.setSessionFactory(sessionFactory);
//        return interceptor;
//    }
//    
//    @Bean
//    public OpenEntityManagerInViewFilter openEntityManagerInViewFilte() {
//    	OpenEntityManagerInViewFilter interceptor = new OpenEntityManagerInViewFilter();
//    	interceptor.setEntityManagerFactoryBeanName("entityManagerFactory");
//    	return interceptor;
//    }
    
//    public Properties hibernateProperties() {
//        Properties props = new Properties();
//
//        props.put(org.hibernate.cfg.Environment.FORMAT_SQL, "true");
//        props.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
//        props.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");
//
//        props.put(org.hibernate.cfg.Environment.POOL_SIZE, 30);
//
//        props.setProperty(AUTOCOMMIT, "false");
//        props.setProperty(RELEASE_CONNECTIONS, "after_transaction");
//
//        // Secondary Cache
//        props.put(org.hibernate.cfg.Environment.USE_SECOND_LEVEL_CACHE, true);
//        props.put(org.hibernate.cfg.Environment.USE_QUERY_CACHE, true);
//        props.put(org.hibernate.cfg.Environment.CACHE_REGION_FACTORY, RedisRegionFactory.class.getName());
//        props.put(org.hibernate.cfg.Environment.CACHE_REGION_PREFIX, "hibernate");
//        props.put(org.hibernate.cfg.Environment.CACHE_PROVIDER_CONFIG, "conf/hibernate-redis.properties");
//
//        props.setProperty(org.hibernate.cfg.Environment.GENERATE_STATISTICS, "true");
//        props.setProperty(org.hibernate.cfg.Environment.USE_STRUCTURED_CACHE, "true");
//
//        props.setProperty(org.hibernate.cfg.Environment.TRANSACTION_COORDINATOR_STRATEGY, JtaTransactionCoordinatorBuilderImpl.class.getName());
//
//
//        return props;
//      }
    
//    @Bean
//    public SessionFactory sessionFactory() throws IOException {
//      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//      factoryBean.setPackagesToScan("com.ces.*.pojo");
//      factoryBean.setDataSource(dataSource);
//      factoryBean.setHibernateProperties(hibernateProperties());
//
//      factoryBean.afterPropertiesSet();
//
//      return factoryBean.getObject();
//    }
//    
//    @Bean
//    public PlatformTransactionManager transactionManager(SessionFactory sf) throws IOException {
//      return new HibernateTransactionManager(sf);
//    }
    
}
