package ru.ivanov.todoproject.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.enterprise.inject.Produces;

public class HibernateConnectionFactory {

    @Produces
    public SessionFactory createSessionFactory() {
//        final Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
//        final StandardServiceRegistryBuilder serviceRegistryBuilder =
//                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//        return configuration.buildSessionFactory(serviceRegistryBuilder.build());

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
}
