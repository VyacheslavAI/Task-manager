package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.api.IUserSOAPEndpoint;
import ru.ivanov.todoproject.api.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class UserReadCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "read user";
    }

    @Override
    public String getDescription() {
        return "Command to print information about user";
    }

    @Override
    public void execute(final SOAPServiceLocator soapServiceLocator) {
        IUserSOAPEndpoint userSOAPEndpoint = soapServiceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort();
        ConsoleHelper.printMessage("Enter user login:");
        final String userLogin = ConsoleHelper.readString();
        final User user = userSOAPEndpoint.readUser(userLogin);
        if (user == null) {
            ConsoleHelper.printMessage(String.format("User %s not found", userLogin));
            return;
        }
        final String format = "Login: %s %nPassword: %s %nDate of creation: %s";
        ConsoleHelper.printMessage(String.format(format,
                user.getLogin(),
                user.getPassword(),
                ConsoleHelper.formatDate(user.getCreated())));
    }
}
