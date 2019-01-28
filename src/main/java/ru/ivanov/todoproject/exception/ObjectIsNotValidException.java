package ru.ivanov.todoproject.exception;

public class ObjectIsNotValidException extends Exception {

    public ObjectIsNotValidException() {
    }

    public ObjectIsNotValidException(final String message) {
        super(message);
    }
}
