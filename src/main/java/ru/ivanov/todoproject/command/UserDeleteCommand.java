package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class UserDeleteCommand extends Command {

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
        ConsoleHelper.printMessage("Enter user login for delete:");
        final String userLogin = ConsoleHelper.readString();
        final User user = serviceLocator.getUserService().loadUserByLogin(userLogin);
        if (user == null) {
            ConsoleHelper.printMessage(String.format("User %s not found", userLogin));
            return;
        }
        serviceLocator.getUserService().deleteUser(user);
        ConsoleHelper.printMessage(String.format("User %s has been deleted", userLogin));
    }
}
