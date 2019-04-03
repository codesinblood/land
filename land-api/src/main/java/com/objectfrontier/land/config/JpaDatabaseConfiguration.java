package com.objectfrontier.land.config;

import static com.objectfrontier.land.common.Constant.BASE_PACKAGE;
import static com.objectfrontier.land.common.Constant.ENTITY_MANAGER;
import static com.objectfrontier.land.common.Constant.SPRING_HIKARI;
import static com.objectfrontier.land.common.Constant.TRANSACTION_MANAGER;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author karthik.n
 * Feb 12, 2019 2:31:02 PM
 */

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(
        entityManagerFactoryRef = ENTITY_MANAGER,
        transactionManagerRef = TRANSACTION_MANAGER,
        basePackages = {BASE_PACKAGE})
public class JpaDatabaseConfiguration {

    @Autowired
    JpaVendorAdapter jpaVendorAdapter;

    /* read the property from resources
    set into data source */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = SPRING_HIKARI)
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        return dataSource;
    }

    /* provide entity manager factory with value
    configure hibernate with JPA */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean localEntityManager = new LocalContainerEntityManagerFactoryBean();
        localEntityManager.setDataSource(dataSource());
        localEntityManager.setPackagesToScan(BASE_PACKAGE);
        localEntityManager.setJpaVendorAdapter(jpaVendorAdapter);
        return localEntityManager;
    }

    /* configuration for transaction manager */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    /* access for repository */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
