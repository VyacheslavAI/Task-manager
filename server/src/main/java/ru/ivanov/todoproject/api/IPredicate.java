package ru.ivanov.todoproject.api;

public interface IPredicate<T> {

    boolean apply(T type);
}
