package ru.ivanov.todoproject.dao;

import ru.ivanov.todoproject.api.IRepository;
import ru.ivanov.todoproject.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    final Map<String, E> entities = new HashMap<>();

    public E merge(final E entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    public E findById(final String id) {
        return entities.get(id);
    }

    public List<E> findAll() {
        return new ArrayList<>(entities.values());
    }

    public E delete(final E entity) {
        entities.remove(entity.getId());
        return entity;
    }

    public void deleteAll() {
        entities.clear();
    }

    public void addAll(final List<E> entityList) {
        for (final E entity : entityList) {
            entities.put(entity.getId(), entity);
        }
    }
}
