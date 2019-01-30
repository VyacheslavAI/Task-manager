package ru.ivanov.todoproject.exception;

public class AuthorizationException extends Exception {

    public AuthorizationException() {
        super("Authorization ailed");
    }
}
