package com.board.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by wonhyuk on 2016. 2. 19..
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    @Autowired
    private Environment env;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;
    private static final String DB_DRIVER = "spring.datasource.driverClassName";
    private static final String DB_URL = "spring.datasource.url";
    private static final String DB_USER = "spring.datasource.username";
    private static final String DB_PASS = "spring.datasource.password";
    private static final String HIBERNATE_DIALECT = "spring.jpa.properties.hibernate.dialect";
    private static final String HIBERNATE_DDL = "spring.jpa.hibernate.ddl-auto";
    private static final String SHOW_SQL = "spring.jpa.show-sql";
    private static final String PACKAGE_SCAN = "entitymanager.packagesToScan";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(DB_DRIVER));
        dataSource.setUrl(env.getProperty(DB_URL));
        dataSource.setUsername(env.getProperty(DB_USER));
        dataSource.setPassword(env.getProperty(DB_PASS));
        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(env.getProperty(PACKAGE_SCAN));
        // Vendor adapter
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        // Hibernate properties
        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.dialect", env.getProperty(HIBERNATE_DIALECT));
        additionalProperties.put("hibernate.show_sql", env.getProperty(SHOW_SQL));
        additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty(HIBERNATE_DDL));
        entityManagerFactory.setJpaProperties(additionalProperties);
        return entityManagerFactory;
    }
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory.getObject());
        return transactionManager;
    }
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}