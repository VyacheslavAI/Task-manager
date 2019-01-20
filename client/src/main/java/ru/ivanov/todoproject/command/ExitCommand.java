package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class ExitCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Command to exit from application";
    }

    @Override
    public void execute(final SOAPServiceLocator soapServiceLocator) {
        ConsoleHelper.printMessage("Good bye!");
    }
}
