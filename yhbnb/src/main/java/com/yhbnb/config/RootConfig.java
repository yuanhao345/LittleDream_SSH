package com.yhbnb.config;

import com.jolbox.bonecp.BoneCPDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Configuration
@PropertySource(value = {"classpath:config.properties"})
@EnableJpaRepositories(basePackages = "com.yhbnb.repository")
@ComponentScan(basePackages = {"com.yhbnb.controller", "com.yhbnb.service"})
@EnableTransactionManagement
@Slf4j
public class RootConfig {

    /**
     * 系统环境变量，包含系统资源文件内容，默认加载wisdom.properties资源文件
     */
    private Environment env;


    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }



    /**
     * 数据源实例化
     *
     * @return 数据源
     */
    @Bean
    public BoneCPDataSource dataSource() {
        BoneCPDataSource ds = new BoneCPDataSource();  // create a new datasource object
        ds.setDriverClass(env.getProperty("jdbc.driverClassName"));
        ds.setJdbcUrl(env.getProperty("jdbc.url"));		// set the JDBC url
        ds.setUsername(env.getProperty("jdbc.username"));				// set the username
        ds.setPassword(env.getProperty("jdbc.password"));
        ds.setMaxConnectionsPerPartition(20);
        ds.setMinConnectionsPerPartition(10);
        ds.setAcquireIncrement(5);
        ds.setPoolAvailabilityThreshold(20);
        ds.setIdleMaxAge(240,TimeUnit.MINUTES);
        ds.setIdleConnectionTestPeriod(60, TimeUnit.MINUTES);
        ds.setStatementsCacheSize(20);
        return ds;
    }

    //获取sessionFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(BoneCPDataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean localSession = new LocalContainerEntityManagerFactoryBean();
        localSession.setDataSource(dataSource);
        localSession.setJpaVendorAdapter(jpaVendorAdapter);
        localSession.setPackagesToScan("com.yhbnb.entity");
        Properties properties = new Properties();
        properties.setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        localSession.setJpaProperties(properties);
        return localSession;
    }




/*    *//**
     * 事务实例化
     *
     *
     * @return jpa事务管理器
     *//*
    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }*/


    //jpa规范
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabase(Database.MYSQL);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        return adapter;
    }

    //事务
    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }




}