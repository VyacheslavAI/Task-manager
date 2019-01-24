package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.IUserSOAPEndpoint;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.api.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.Date;

public class UserUpdateCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "edit user";
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
    public void execute(final ServiceLocator serviceLocator) {
        final IUserSOAPEndpoint userSOAPEndpoint = serviceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort();
        final Session session = serviceLocator.getSession();
        ConsoleHelper.printMessage("Enter user login for update:");
        final String userLogin = ConsoleHelper.readString();
        final User user = userSOAPEndpoint.readUser(session, userLogin);
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
        user.setCreated(ConsoleHelper.convertDateToXMLCalendar(newDate));
        user.setPasswordHash(newPassword);
        userSOAPEndpoint.updateUser(session, user);
        ConsoleHelper.printMessage(String.format("User %s has been updated", userLogin));
    }
}
