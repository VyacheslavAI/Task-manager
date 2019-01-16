package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class ExitCommand implements Command {

    @Override
    public void execute(Controller controller) {
        ConsoleHelper.printMessage("Good bye!");
    }
}
