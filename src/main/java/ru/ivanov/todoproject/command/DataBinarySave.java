package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.util.ConsoleHelper;
import ru.ivanov.todoproject.util.SerializationUtil;

import java.io.IOException;

public class DataBinarySave implements Command {

    @Override
    public void execute(Controller controller) {
        try {
            SerializationUtil.serialize(controller);
            ConsoleHelper.printMessage("Serialization successful");
        } catch (IOException e) {
            ConsoleHelper.printMessage("An error has occurred during serialization");
        }
    }
}
