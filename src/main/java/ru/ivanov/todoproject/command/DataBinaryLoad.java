package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.util.ConsoleHelper;
import ru.ivanov.todoproject.util.SerializationUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataBinaryLoad implements Command {

    @Override
    public void execute(Controller controller) {
        try {
            controller.clearAllEntity();
            Map<String, List> entities = SerializationUtil.deserialize();
            controller.getProjectService().addAllProject(entities.get("Project"));
            controller.getTaskService().addAllTask(entities.get("Task"));
            ConsoleHelper.printMessage("Deserialization successful");
        } catch (IOException | ClassNotFoundException e) {
            ConsoleHelper.printMessage("An error has occurred during deserialization");
        }
    }
}
