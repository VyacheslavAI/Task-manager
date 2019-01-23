package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.IRepository;
import ru.ivanov.todoproject.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    final Map<String, E> entities = new HashMap<>();

    public E merge(final E entity) {
        if (entity == null) return null;
        entities.put(entity.getId(), entity);
        return entity;
    }

    public E findById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return entities.get(id);
    }

    public List<E> findAll() {
        return new ArrayList<>(entities.values());
    }

    public E delete(final E entity) {
        if (entity == null) return null;
        entities.remove(entity.getId());
        return entity;
    }

    public boolean deleteAll() {
        entities.clear();
        return false;
    }

    public boolean addAll(final List<E> entityList) {
        if (entityList == null) return false;
        for (final E entity : entityList) {
            entities.put(entity.getId(), entity);
        }
        return false;
    }
}
