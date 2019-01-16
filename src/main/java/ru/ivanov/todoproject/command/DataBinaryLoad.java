package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.util.Domain;
import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataBinaryLoad implements Command {

    @Override
    public void execute(Controller controller) {
        try (final ObjectInput objectInput = new ObjectInputStream(Files.newInputStream(Paths.get("data.bin")))) {
            controller.clearAllEntity();
            final Domain domain = (Domain) objectInput.readObject();
            controller.getProjectService().addAllProject(domain.getProjects());
            controller.getTaskService().addAllTask(domain.getTasks());
            ConsoleHelper.printMessage("Deserialization successful");
        } catch (IOException | ClassNotFoundException e) {
            ConsoleHelper.printMessage("An error has occurred during deserialization");
        }
    }
}
