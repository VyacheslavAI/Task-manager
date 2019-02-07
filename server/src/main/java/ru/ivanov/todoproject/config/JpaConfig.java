package ru.ivanov.todoproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.ivanov.todoproject.repository")
public class JpaConfig {

    @Bean("entityManagerFactory")
    public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
        final LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("jpamanager");
        return factoryBean;
    }

    @Bean("transactionManager")
    public JpaTransactionManager getJpaTransactionManager() {
        return new JpaTransactionManager();
    }
}
