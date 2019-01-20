package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class SignInCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "login";
    }

    @Override
    public String getDescription() {
        return "Command to login user";
    }

    @Override
    public void execute(final SOAPServiceLocator soapServiceLocator) {
        ConsoleHelper.printMessage("Welcome");
    }
}
