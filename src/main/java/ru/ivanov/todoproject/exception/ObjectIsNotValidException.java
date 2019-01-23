package ru.ivanov.todoproject.exception;

import ru.ivanov.todoproject.entity.AbstractEntity;

public class ObjectIsNotValidException extends Exception {

    public ObjectIsNotValidException(final AbstractEntity entity) {
        super(String.format("%s with id: %s has unset necessary fields",
                entity.getClass().getSimpleName(), entity.getId()));
    }
}
