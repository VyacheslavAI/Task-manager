package ru.ivanov.todoproject.exception;

public class ObjectIsNotValidException extends Exception {

    public ObjectIsNotValidException() {
        super("Required fields not filled");
    }
}
