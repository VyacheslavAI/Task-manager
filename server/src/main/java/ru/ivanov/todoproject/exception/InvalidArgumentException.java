package ru.ivanov.todoproject.exception;

public class InvalidArgumentException extends Exception {

    public InvalidArgumentException() {
        super("Argument cannot be empty or null");
    }
}
