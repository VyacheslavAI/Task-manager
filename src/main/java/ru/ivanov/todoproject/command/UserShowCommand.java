package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;

public class UserShowCommand implements Command {

    @Override
    public String getConsoleCommand() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isAuthorizationRequired() {
        return false;
    }

    @Override
    public void execute(Bootstrap bootstrap) {

    }
}
