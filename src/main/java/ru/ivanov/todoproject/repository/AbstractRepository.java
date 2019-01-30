package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.IRepository;
import ru.ivanov.todoproject.entity.AbstractEntity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    Connection connection;

    final Map<String, E> entities = new HashMap<>();

    @Override
    public E merge(final E entity) {
        if (entity == null) return null;
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public E findById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return entities.get(id);
    }

    @Override
    public List<E> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public E delete(final E entity) {
        if (entity == null) return null;
        entities.remove(entity.getId());
        return entity;
    }

    @Override
    public boolean deleteAll() {
        entities.clear();
        return true;
    }

    @Override
    public boolean addAll(final List<E> entityList) {
        if (entityList == null) return false;
        for (final E entity : entityList) {
            entities.put(entity.getId(), entity);
        }
        return true;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
