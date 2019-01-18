package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HelpCommand extends Command {

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
    public void execute(final ServiceLocator serviceLocator) {
        final boolean hasAuthorizedUser = serviceLocator.getUserService().hasUserAuthorized();
        final List<Command> availableCommands = new ArrayList<>();
        final Iterator<Command> commandIterator = availableCommands.iterator();
        while (commandIterator.hasNext()) {
            final Command command = commandIterator.next();
            if (command.isAuthorizationRequired() && !hasAuthorizedUser) {
                commandIterator.remove();
            }
        }

        ConsoleHelper.printMessage("The following commands are available to you:");
        for (Command command : availableCommands) {
            final String consoleCommand = command.getConsoleCommand();
            final String description = command.getDescription();
            ConsoleHelper.printMessage(String.format("%s - %s", consoleCommand, description));
        }
    }
}
