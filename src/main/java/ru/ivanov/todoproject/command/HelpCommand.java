package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class HelpCommand implements Command {

    @Override
    public String getConsoleCommand() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Print all available commands";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return false;
    }

    @Override
    public void execute(final Bootstrap bootstrap) {
        final List<Command> availableCommands = bootstrap.getListAvailableCommands();
        ConsoleHelper.printMessage("The following commands are available to you:");
        for (Command command : availableCommands) {
            final String consoleCommand = command.getConsoleCommand();
            final String description = command.getDescription();
            ConsoleHelper.printMessage(String.format("%s - %s", consoleCommand, description));
        }
    }
}
