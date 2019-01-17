package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.util.ConsoleHelper;
import ru.ivanov.todoproject.dto.Domain;

import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataBinaryLoad implements Command {

    @Override
    public String getConsoleCommand() {
        return "load bin";
    }

    @Override
    public String getDescription() {
        return "Command for load application state from binary file";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(Bootstrap bootstrap) {
        try (final InputStream inputStream = Files.newInputStream(Paths.get("data.bin"));
             final ObjectInput objectInput = new ObjectInputStream(inputStream)) {
            bootstrap.clearAllProjectAndTask();
            final Domain domain = (Domain) objectInput.readObject();
            bootstrap.getProjectService().addAllProject(domain.getProjects());
            bootstrap.getTaskService().addAllTask(domain.getTasks());
            bootstrap.getUserService().addAllUser(domain.getUsers());
            ConsoleHelper.printMessage("Loading from binary file was successful");
        } catch (Exception e) {
            ConsoleHelper.printMessage("An error has occurred during loading from binary file");
        }
    }
}
