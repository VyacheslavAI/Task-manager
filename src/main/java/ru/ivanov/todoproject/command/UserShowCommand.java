package ru.ivanov.todoproject.command;


import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.User;
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
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        ConsoleHelper.printMessage("All users:");
        final List<User> users = serviceLocator.getUserService().loadAllUser();
        final String format = "Id: %s%nLogin: %s%nPassword: %s%nDate of creation: %s";
        for (final User user : users) {
            ConsoleHelper.printMessage(String.format(format,
                    user.getId(),
                    user.getLogin(),
                    user.getPassword(),
                    user.getCreated()));
            ConsoleHelper.printDelimiter();
        }
    }
}
