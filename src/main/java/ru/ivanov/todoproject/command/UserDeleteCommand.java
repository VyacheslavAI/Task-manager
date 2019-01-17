package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class UserDeleteCommand implements Command {

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
    public void execute(Bootstrap bootstrap) {
        ConsoleHelper.printMessage("Enter user login for delete:");
        final String userLogin = ConsoleHelper.readString();
        User user = bootstrap.getUserService().loadUserByLogin(userLogin);
        if (user == null) {
            ConsoleHelper.printMessage(String.format("User %s not found", userLogin));
            return;
        }
        bootstrap.getUserService().deleteUser(user);
        ConsoleHelper.printMessage(String.format("User %s has been deleted", userLogin));
    }
}
