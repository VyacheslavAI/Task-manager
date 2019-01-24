package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.*;

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
        ConsoleHelper.printMessage("The following commands are available to you:");
        final Map<String, Command> commands = ((Bootstrap) serviceLocator).getCommands();
        final List<Command> availableCommands = new ArrayList<>(commands.values());

        Collections.sort(availableCommands, new Comparator<Command>() {
            @Override
            public int compare(final Command c1, final Command c2) {
                final String consoleCommandOne = c1.getConsoleCommand();
                final String consoleCommandTwo = c2.getConsoleCommand();
                return consoleCommandOne.compareTo(consoleCommandTwo);
            }
        });

        for (final Command command : availableCommands) {
            final String consoleCommand = command.getConsoleCommand();
            final String description = command.getDescription();
            ConsoleHelper.printMessage(String.format("%s - %s", consoleCommand, description));
        }
    }
}
