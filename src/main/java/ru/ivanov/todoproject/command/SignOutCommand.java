package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class SignOutCommand implements Command {

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
    public void execute(Bootstrap bootstrap) {
        bootstrap.setActiveUser(null);
        ConsoleHelper.printMessage("Logout successful");
    }
}
