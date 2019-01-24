package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.IUserSOAPEndpoint;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.api.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class UserReadCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "read user";
    }

    @Override
    public String getDescription() {
        return "Command to print information about user";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        IUserSOAPEndpoint userSOAPEndpoint = serviceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort();
        final Session session = serviceLocator.getSession();
        ConsoleHelper.printMessage("Enter user login:");
        final String userLogin = ConsoleHelper.readString();
        final User user = userSOAPEndpoint.readUser(session, userLogin);
        if (user == null) {
            ConsoleHelper.printMessage(String.format("User %s not found", userLogin));
            return;
        }
        final String format = "Login: %s %nPassword: %s %nDate of creation: %s";
        ConsoleHelper.printMessage(String.format(format,
                user.getLogin(),
                user.getPasswordHash(),
                ConsoleHelper.formatDate(user.getCreated())));
    }
}
