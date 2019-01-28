package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.IUserSOAPEndpoint;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.api.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class UserDeleteCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "delete user";
    }

    @Override
    public String getDescription() {
        return "Command to delete user";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        ConsoleHelper.print("Enter user login for delete:");
        final Session session = serviceLocator.getSession();
        final String userLogin = ConsoleHelper.readString();
        IUserSOAPEndpoint userSOAPEndpoint = serviceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort();
        final User user = userSOAPEndpoint.readUser(session, userLogin);
        if (user == null) {
            ConsoleHelper.print(String.format("User %s not found", userLogin));
            return;
        }
        userSOAPEndpoint.deleteUser(session, user);
        ConsoleHelper.print(String.format("User %s has been deleted", userLogin));
    }
}
