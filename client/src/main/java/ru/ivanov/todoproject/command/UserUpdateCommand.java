package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.api.IUserSOAPEndpoint;
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
    public void execute(final SOAPServiceLocator soapServiceLocator) {
        final IUserSOAPEndpoint userSOAPEndpoint = soapServiceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort();
        ConsoleHelper.printMessage("Enter user login for update:");
        final String userLogin = ConsoleHelper.readString();
        final User user = userSOAPEndpoint.readUser(userLogin);
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
        user.setPassword(newPassword);
        userSOAPEndpoint.updateUser(user);
        ConsoleHelper.printMessage(String.format("User %s has been updated", userLogin));
    }
}
