package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.*;

public class HelpCommand extends AbstractCommand {

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
    public void executeCommand() {
        ConsoleHelper.print("The following commands are available to you:");
        final Map<String, AbstractCommand> commands = ((Bootstrap) serviceLocator).getCommands();
        final List<AbstractCommand> availableCommands = new ArrayList<>(commands.values());

        Collections.sort(availableCommands, new Comparator<AbstractCommand>() {
            @Override
            public int compare(final AbstractCommand c1, final AbstractCommand c2) {
                final String consoleCommandOne = c1.getConsoleCommand();
                final String consoleCommandTwo = c2.getConsoleCommand();
                return consoleCommandOne.compareTo(consoleCommandTwo);
            }
        });

        for (final AbstractCommand command : availableCommands) {
            final String consoleCommand = command.getConsoleCommand();
            final String description = command.getDescription();
            ConsoleHelper.print(String.format("%s - %s", consoleCommand, description));
        }
    }
}
