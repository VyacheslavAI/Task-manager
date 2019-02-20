package ru.ivanov.todoproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.ivanov.todoproject.repository")
@PropertySource("classpath:application.properties")
public class JpaConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(final Environment environment) {
        final DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        managerDataSource.setUrl(environment.getRequiredProperty("db.url"));
        managerDataSource.setUsername(environment.getRequiredProperty("db.username"));
        managerDataSource.setPassword(environment.getRequiredProperty("db.password"));
        return managerDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource, final Environment environment) {
        final LocalContainerEntityManagerFactoryBean factoryBean
                = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("ru.ivanov.todoproject");

        final Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));

        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}