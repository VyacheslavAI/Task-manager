package ru.ivanov.todoproject.exception;

public class ObjectNotFoundException extends Exception {

    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(final String message) {
        super(message);
    }
}
