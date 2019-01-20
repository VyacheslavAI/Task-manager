package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class SignOutCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "logout";
    }

    @Override
    public String getDescription() {
        return "Command to logout user";
    }


    @Override
    public void execute(final SOAPServiceLocator soapServiceLocator) {
        ConsoleHelper.printMessage("Logout successful");
    }
}
