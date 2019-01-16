package ru.ivanov.todoproject.api;

import java.util.List;

public interface IRepository<E> {

    E merge(final E entity);

    List<E> getEntitiesByName(final String name);

    E deleteEntity(final E entity);

    List<E> getAllEntity();

    void deleteAllEntity();

    void addAllEntity(final List<E> entityList);
}
