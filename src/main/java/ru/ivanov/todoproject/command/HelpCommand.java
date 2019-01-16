package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.Operation;
import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class HelpCommand implements Command {

    @Override
    public void execute(Controller controller) {
        ConsoleHelper.printMessage("The following commands are available to you:");

        for (String command : Operation.commands) {
            ConsoleHelper.printMessage(command);
        }
    }
}
