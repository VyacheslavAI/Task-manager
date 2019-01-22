package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.api.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class UserCreateCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "create user";
    }

    @Override
    public String getDescription() {
        return "Command to create user";
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        ConsoleHelper.printMessage("Enter new user login:");
        final Session session = serviceLocator.getSession();
        final String userLogin = ConsoleHelper.readString();
        ConsoleHelper.printMessage("Enter new user password:");
        final String userPassword = ConsoleHelper.readString();
        final User user = new User();
        user.setLogin(userLogin);
        user.setPassword(userPassword);
        serviceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort().createUser(session, user);
        ConsoleHelper.printMessage(String.format("User %s has been added", userLogin));
    }
}
