package ru.ivanov.todoproject.util;

public final class ConsoleHelper {

    private ConsoleHelper() {
        throw new AssertionError();
    }

    public static void print(final String message) {
        System.out.println(message);
    }
}
