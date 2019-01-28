package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class SignOutCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "logout";
    }

    @Override
    public String getDescription() {
        return "Command to logout user";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        serviceLocator.setSession(null);
        ConsoleHelper.print("Logout successful");
    }
}
