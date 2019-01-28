package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.IUserSOAPEndpoint;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.api.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.Date;

public class UserUpdateCommand extends AbstractCommand {

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
        ConsoleHelper.print("Enter user login for update:");
        final String userLogin = ConsoleHelper.readString();
        final User user = userSOAPEndpoint.readUser(session, userLogin);
        if (user == null) {
            ConsoleHelper.print(String.format("User %s not found", userLogin));
            return;
        }

        ConsoleHelper.print("Please enter new login:");
        final String newLogin = ConsoleHelper.readString();
        ConsoleHelper.print("Please enter new date of creation(example: 04/01/1993):");
        final String date = ConsoleHelper.readString();
        final Date newDate = ConsoleHelper.parseDate(date);
        ConsoleHelper.print("Please enter new password:");
        final String newPassword = ConsoleHelper.readString();
        user.setLogin(newLogin);
        user.setCreated(ConsoleHelper.convertDateToXMLCalendar(newDate));
        user.setPasswordHash(newPassword);
        userSOAPEndpoint.updateUser(session, user);
        ConsoleHelper.print(String.format("User %s has been updated", userLogin));
    }
}
