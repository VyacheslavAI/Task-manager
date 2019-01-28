package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class ExitCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Command to exit from application";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return false;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        ConsoleHelper.print("Good bye!");
    }
}
