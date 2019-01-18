package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class SignInCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "login";
    }

    @Override
    public String getDescription() {
        return "Command to login user";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return false;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        ConsoleHelper.printMessage("Please enter your login:");
        final String userLogin = ConsoleHelper.readString();
        final User user = serviceLocator.getUserService().loadUserByLogin(userLogin);
        ConsoleHelper.printMessage("Please enter your password");
        final String userPassword = ConsoleHelper.readString();
        if (user == null || !user.getPassword().equals(userPassword)) {
            ConsoleHelper.printMessage("Wrong login or password");
            return;
        }
        serviceLocator.getUserService().setActiveUser(user);
        ConsoleHelper.printMessage(String.format("Welcome, %s", userLogin));
    }
}
