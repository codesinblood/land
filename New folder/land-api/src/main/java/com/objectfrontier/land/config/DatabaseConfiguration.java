package com.objectfrontier.land.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

import static com.objectfrontier.land.common.Constant.BASE_PACKAGE;
import static com.objectfrontier.land.common.Constant.SPRING_HIKARI;

/**
 * @author karthik.n
 * @since 1.0.0
 * Feb 11, 2019 11:11:10 AM
 */

/*
created bean for session factory to get the session
also readed the property to access the database*/

/**
 * Below lines are commented to avoid initialization of hibernate configuration
 */

//@Configuration
//@EnableTransactionManagement
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@ComponentScan(basePackages = BASE_PACKAGE)
public class DatabaseConfiguration {

    @Autowired
    private LocalSessionFactoryBean localSessionFactory;

    @Autowired
    private HikariDataSource dataSource;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        localSessionFactory.setDataSource(dataSource());
        localSessionFactory.setPackagesToScan(BASE_PACKAGE);
        return localSessionFactory;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = SPRING_HIKARI)
    public DataSource dataSource() {
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
} 
