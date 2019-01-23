package ru.ivanov.todoproject.api;

import java.util.List;

public interface IRepository<E> {

    E merge(final E entity);

    E delete(final E entity);

    List<E> findAll();

    E findById(final String id);

    boolean deleteAll();

    boolean addAll(final List<E> entityList);
}
