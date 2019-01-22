package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.IUserSOAPEndpoint;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.api.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class UserShowCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "show user";
    }

    @Override
    public String getDescription() {
        return "Command to print information about users";
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        final IUserSOAPEndpoint userSOAPEndpoint = serviceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort();
        final Session session = serviceLocator.getSession();
        ConsoleHelper.printMessage("All users:");
        final List<User> users = userSOAPEndpoint.showUsers(session);
        final String format = "Login: %s%nPassword: %s%nDate of creation: %s";
        for (final User user : users) {
            ConsoleHelper.printMessage(String.format(format,
                    user.getLogin(),
                    user.getPassword(),
                    user.getCreated()));
            ConsoleHelper.printDelimiter();
        }
    }
}
