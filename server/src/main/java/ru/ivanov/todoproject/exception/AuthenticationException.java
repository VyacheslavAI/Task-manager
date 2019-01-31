package ru.ivanov.todoproject.exception;

public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super("Authentication failed");
    }
}
