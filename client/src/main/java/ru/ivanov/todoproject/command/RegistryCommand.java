package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class RegistryCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "registry";
    }

    @Override
    public String getDescription() {
        return "New User Registration";
    }

    @Override
    public void execute(ServiceLocator serviceLocator) {
        ConsoleHelper.printMessage("Enter new name:");
        final String login = ConsoleHelper.readString();
        ConsoleHelper.printMessage("Enter password:");
        final String password = ConsoleHelper.readString();
        boolean result = serviceLocator.getSessionSOAPEndpointService().getSessionSOAPEndpointPort().userRegistry(login, password);
        ConsoleHelper.printMessage(Boolean.toString(result));
    }
}
