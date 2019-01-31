package ru.ivanov.todoproject.exception;

public class ObjectNotFoundException extends Exception {

    public ObjectNotFoundException() {
        super("Object not found");
    }
}
