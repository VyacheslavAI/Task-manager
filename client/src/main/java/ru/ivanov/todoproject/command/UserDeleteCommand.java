package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.api.IUserSOAPEndpoint;
import ru.ivanov.todoproject.api.User;
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
    public void execute(final SOAPServiceLocator soapServiceLocator) {
        ConsoleHelper.printMessage("Enter user login for delete:");
        final String userLogin = ConsoleHelper.readString();
        IUserSOAPEndpoint userSOAPEndpoint = soapServiceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort();
        final User user = userSOAPEndpoint.readUser(userLogin);
        if (user == null) {
            ConsoleHelper.printMessage(String.format("User %s not found", userLogin));
            return;
        }
        userSOAPEndpoint.deleteUser(user);
        ConsoleHelper.printMessage(String.format("User %s has been deleted", userLogin));
    }
}
