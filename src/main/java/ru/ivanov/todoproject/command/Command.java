package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;

public interface Command {

    String getConsoleCommand();

    String getDescription();

    boolean isAuthorizationRequired();

    void execute(final Bootstrap bootstrap);
}
