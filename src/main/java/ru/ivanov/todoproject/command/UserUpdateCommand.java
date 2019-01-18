package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.Date;

public class UserUpdateCommand extends Command {

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
    public void execute(ServiceLocator serviceLocator) {
        ConsoleHelper.printMessage("Enter user login for update:");
        final String userLogin = ConsoleHelper.readString();
        User user = serviceLocator.getUserService().loadUserByLogin(userLogin);
        if (user == null) {
            ConsoleHelper.printMessage(String.format("User %s not found", userLogin));
            return;
        }

        ConsoleHelper.printMessage("Please enter new login:");
        final String newLogin = ConsoleHelper.readString();
        ConsoleHelper.printMessage("Please enter new date of creation(example: 04/01/1993):");
        final String date = ConsoleHelper.readString();
        final Date newDate = ConsoleHelper.parseDate(date);
        ConsoleHelper.printMessage("Please enter new password:");
        final String newPassword = ConsoleHelper.readString();
        user.setLogin(newLogin);
        user.setCreated(newDate);
        user.setPassword(newPassword);

        ConsoleHelper.printMessage(String.format("User %s has been updated", userLogin));
    }


}
