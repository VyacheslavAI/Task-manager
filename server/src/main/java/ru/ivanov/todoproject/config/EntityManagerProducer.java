package ru.ivanov.todoproject.config;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityManagerProducer {

    private EntityManagerFactory entityManagerFactory;

    @Produces
    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("manager").createEntityManager();
    }

    public void close(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) entityManager.close();
    }
}