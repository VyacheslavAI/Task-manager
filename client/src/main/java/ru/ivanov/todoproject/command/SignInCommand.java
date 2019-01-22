package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.Session;
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
    public void execute(final ServiceLocator serviceLocator) {
        ConsoleHelper.printMessage("Enter your login:");
        final String login = ConsoleHelper.readString();
        ConsoleHelper.printMessage("Enter password:");
        final String password = ConsoleHelper.readString();
        final Session session = serviceLocator.getSessionSOAPEndpointService().getSessionSOAPEndpointPort().singIn(login, password);
        if (session == null) {
            ConsoleHelper.printMessage("Wrong login/password");
            return;
        }
        serviceLocator.setSession(session);
        ConsoleHelper.printMessage("Welcome, " + login);
    }
}
