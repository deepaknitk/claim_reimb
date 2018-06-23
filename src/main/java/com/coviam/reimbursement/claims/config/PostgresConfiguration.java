package com.coviam.reimbursement.claims.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Foram Shah on 23/06/18
 */
@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@ComponentScan(basePackages = {"com.coviam.reimbursement.claims.entity",
    "com.coviam.reimbursement.claims.repository"})
@EntityScan(basePackages = {"com.coviam.reimbursement.claims.entity"})
@EnableJpaRepositories(entityManagerFactoryRef = "postgresEntityManagerFactory",
    transactionManagerRef = "postgresTransactionManager",
    basePackages = {"com.coviam.reimbursement.claims.repository"})
public class PostgresConfiguration {

    @Autowired private SpringDataSourceConfig springDataSourceConfig;

    @Bean public DataSource dataSource() {
        Properties properties = new Properties();
        properties.setProperty("spring.datasource.initial-size", this.springDataSourceConfig.getInitialSize());
        properties.setProperty("spring.datasource.max-active", this.springDataSourceConfig.getMaxActive());
        properties.setProperty("spring.datasource.max-wait", this.springDataSourceConfig.getMaxWait());
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.springDataSourceConfig.getDriverClassName());
        dataSource.setUrl(this.springDataSourceConfig.getUrl());
        dataSource.setUsername(this.springDataSourceConfig.getUsername());
        dataSource.setPassword(this.springDataSourceConfig.getPassword());
        dataSource.setConnectionProperties(properties);
        return dataSource;
    }

    @Bean(name = "postgresEntityManagerFactory") public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[] {"com.coviam.reimbursement.claims.entity"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(jpaProperties());

        return entityManagerFactoryBean;
    }

    @Bean("postgresTransactionManager") public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", this.springDataSourceConfig.getDdlAuto());
        properties.setProperty("hibernate.dialect", this.springDataSourceConfig.getDialect());
        properties.setProperty("hibernate.show_sql", this.springDataSourceConfig.getShowSql());
        return properties;
    }
}
