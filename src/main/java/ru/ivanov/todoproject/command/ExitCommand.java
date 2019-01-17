package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class ExitCommand implements Command {

    @Override
    public String getConsoleCommand() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Command for exit from application";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return false;
    }

    @Override
    public void execute(Bootstrap bootstrap) {
        ConsoleHelper.printMessage("Good bye!");
    }
}
