package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.IRepository;
import ru.ivanov.todoproject.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    @Override
    public E merge(final E entity) {
        if (entity == null) return null;
        return entity;
    }

    @Override
    public E findById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return null;
    }

    @Override
    public List<E> findAll() {
        return new ArrayList<>();
    }

    @Override
    public E delete(final E entity) {
        if (entity == null) return null;
        return entity;
    }

    @Override
    public boolean deleteAll() {
        return true;
    }

    @Override
    public boolean addAll(final List<E> entityList) {
        if (entityList == null) return false;
        for (final E entity : entityList) {
        }
        return true;
    }
}
