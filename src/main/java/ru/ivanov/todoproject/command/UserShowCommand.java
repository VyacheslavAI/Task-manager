package ru.ivanov.todoproject.command;


import ru.ivanov.todoproject.api.ServiceLocator;

public class UserShowCommand extends Command {

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
    public void execute(ServiceLocator serviceLocator) {

    }
}
