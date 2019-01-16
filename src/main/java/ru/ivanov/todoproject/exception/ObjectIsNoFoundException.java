package ru.ivanov.todoproject.exception;

public class ObjectIsNoFoundException extends Exception {

   // public ObjectIsNoFoundException(String message) {
   //
   // }

    public ObjectIsNoFoundException() {
        super("Object is not found.");
    }

}
