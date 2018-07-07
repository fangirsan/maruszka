package com.maruszka.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.maruszka")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig implements WebMvcConfigurer {

	// set up variable to hold the properties
	// dataSource is using this to bootstrap DB properties
	@Autowired
	private Environment env;
	
	// set up a logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	// define a bean for ViewResolver
	// the ViewResolver maps view names to actual views
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	// database configuration
	// db properties are stored in external file,
	// than are passed to "env"
	@Bean
	public DataSource dataSource() {
		
		// create connection pool
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		// set the jdbc driver
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");		
		}
		catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		// for sanity's sake, let's log url and user ... just to make sure we are reading the data
		logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
		
		// set database connection props
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool props
		dataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		dataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		dataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));		
		dataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return dataSource;
	}
	
//	// define a bean for our security datasource
//	@Bean
//	public DataSource securityDataSource() {
//		
//		// create connection pool
//		ComboPooledDataSource securityDataSource
//									= new ComboPooledDataSource();
//				
//		// set the jdbc driver class
//		try {
//			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
//		} catch (PropertyVetoException exc) {
//			throw new RuntimeException(exc);
//		}
//		
//		// log the connection props
//		// for sanity's sake, log this info
//		// just to make sure we are REALLY reading data from properties file
//		logger.info(">>> jdbc.url=" + env.getProperty("jdbc.url"));
//		logger.info(">>> jdbc.user=" + env.getProperty("jdbc.user"));
//		
//		
//		// set database connection props
//		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
//		securityDataSource.setUser(env.getProperty("jdbc.user"));
//		securityDataSource.setPassword(env.getProperty("jdbc.password"));
//		
//		// set connection pool props
//		securityDataSource.setInitialPoolSize(
//				getIntProperty("connection.pool.initialPoolSize"));
//
//		securityDataSource.setMinPoolSize(
//				getIntProperty("connection.pool.minPoolSize"));
//
//		securityDataSource.setMaxPoolSize(
//				getIntProperty("connection.pool.maxPoolSize"));
//
//		securityDataSource.setMaxIdleTime(
//				getIntProperty("connection.pool.maxIdleTime"));
//		
//		return securityDataSource;
//	}
	
	// need a helper method 
	// read environment property and convert to int
	// Properties are from dataSource --> env
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		// now convert to int
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}
	
	// 
	private Properties getHibernateProperties() {

		// set hibernate properties
		// values are taken from: env --> dataSource
		Properties props = new Properties();

		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return props;				
	}
	
	// FactoryBean that creates a Hibernate SessionFactory.
	// This is the usual way to set up a shared Hibernate
	// SessionFactory in a Spring application context;
	// the SessionFactory can then be passed to Hibernate-based
	// data access objects via dependency injection.
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		// create session factorys
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// set the properties
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}	
	
	// Overloaded method from superclass
	// Method tells Spring where are stored
	// various files, like CSS and others
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }	
	
	// add support for custom message
	@Bean
    public MessageSource messageSource() { 
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("/resources/messages/");
        return messageSource;
    }  
	
	// Setup project time to UTC
//	@PostConstruct
//	public void init() {
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//	}

}
















