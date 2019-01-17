package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class UserCreateCommand implements Command {

    @Override
    public String getConsoleCommand() {
        return "create user";
    }

    @Override
    public String getDescription() {
        return "Command to create user";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(Bootstrap bootstrap) {
        ConsoleHelper.printMessage("Enter new user login:");
        String userLogin = ConsoleHelper.readString();
        ConsoleHelper.printMessage("Enter new user password:");
        String userPassword = ConsoleHelper.readString();
        User user = new User();
        user.setLogin(userLogin);
        user.setPassword(userPassword);
        bootstrap.getUserService().createOrUpdateUser(user);
        ConsoleHelper.printMessage(String.format("User %s has been added", userLogin));
    }
}
