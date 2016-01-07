package com.home.todoList.config;

import com.home.todoList.common.utilities.annotations.environment.Dev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackages = "com.home.todoList")
@PropertySource("classpath:tasklist.properties")
@EnableJpaRepositories("com.home.todoList")
@EnableTransactionManagement
public class AppConfig {

    @Autowired
    Environment environment;

    /*@Autowired
    JpaVendorAdapter jpaVendorAdapter;
*/
    @Bean(name = "dataSource")
//    @Dev
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");

        String url = environment.getProperty("datasource.uat.db.serverUrl");
        String username = environment.getProperty("datasource.uat.db.username");
        String password = environment.getProperty("datasource.uat.db.password");

        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(username);
        driverManagerDataSource.setPassword(password);

        return driverManagerDataSource;
    }

    @Bean
    @Dev
    public JpaVendorAdapter jpaVendorAdapterDev() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }
/*
    @Bean
    @Uat
    public JpaVendorAdapter jpaVendorAdapterUat() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.HSQL);
        jpaVendorAdapter.setGenerateDdl(false);
        return jpaVendorAdapter;
    }
    @Bean
//    @Uat
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }
*/
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setDataSource(dataSource());
        lemfb.setJpaVendorAdapter(jpaVendorAdapter);
        lemfb.setPackagesToScan("com.home.todoList");
        return lemfb;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
