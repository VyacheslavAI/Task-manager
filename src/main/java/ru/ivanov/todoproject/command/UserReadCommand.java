package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class UserReadCommand implements Command {

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
    public void execute(Bootstrap bootstrap) {
        ConsoleHelper.printMessage("Enter user login:");
        final String userLogin = ConsoleHelper.readString();
        User user = bootstrap.getUserService().loadUserByLogin(userLogin);
        if (user == null) {
            ConsoleHelper.printMessage(String.format("User %s not found", userLogin));
            return;
        }
        final String format = "Id: %s %nLogin: %s %nPassword: %s %nDate of creation: %s";
        ConsoleHelper.printMessage(String.format(format,
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                ConsoleHelper.formatDate(user.getCreated())));
    }
}
