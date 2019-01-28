package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class SignInCommand extends AbstractCommand {

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
        ConsoleHelper.print("Enter your login:");
        final String login = ConsoleHelper.readString();
        ConsoleHelper.print("Enter password:");
        final String password = ConsoleHelper.readString();
        final Session session = serviceLocator.getSessionSOAPEndpointService().getSessionSOAPEndpointPort().login(login, password);
        if (session == null) {
            ConsoleHelper.print("Wrong login/password");
            return;
        }
        serviceLocator.setSession(session);
        ConsoleHelper.print("Welcome, " + login);
    }
}
