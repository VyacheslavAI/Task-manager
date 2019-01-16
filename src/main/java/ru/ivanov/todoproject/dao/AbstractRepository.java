package ru.ivanov.todoproject.dao;

import ru.ivanov.todoproject.api.IRepository;
import ru.ivanov.todoproject.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    private final Map<String, E> entities = new HashMap<>();

    public E merge(final E entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    public List<E> getEntitiesByName(final String name) {
        final List<E> result = new ArrayList<>();
        for (final E entity : entities.values()) {
            if (entity.getName().equals(name)) {
                result.add(entity);
            }
        }
        return result;
    }

    public E deleteEntity(final E entity) {
        entities.remove(entity.getId());
        return entity;
    }

    public List<E> getAllEntity() {
        return new ArrayList<>(entities.values());
    }

    public void deleteAllEntity() {
        entities.clear();
    }

    public void addAllEntity(final List<E> entityList) {
        for (final E entity : entityList) {
            entities.put(entity.getId(), entity);
        }
    }
}
