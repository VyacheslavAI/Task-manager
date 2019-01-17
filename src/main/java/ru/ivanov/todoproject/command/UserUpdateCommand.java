package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.util.CommandHelper;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class UserUpdateCommand implements Command {

    @Override
    public String getConsoleCommand() {
        return "update user";
    }

    @Override
    public String getDescription() {
        return "Command to update user";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(Bootstrap bootstrap) {
        ConsoleHelper.printMessage("Enter user login for update:");
        final String userLogin = ConsoleHelper.readString();
        User user = bootstrap.getUserService().loadUserByLogin(userLogin);
        if (user == null) {
            ConsoleHelper.printMessage(String.format("User %s not found", userLogin));
            return;
        }
        CommandHelper.updateUser(user);
        ConsoleHelper.printMessage(String.format("User %s has been updated", userLogin));
    }
}
