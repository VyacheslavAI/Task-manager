package ru.ivanov.todoproject.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import ru.ivanov.todoproject.dto.Domain;
import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataJsonLoad implements Command {

    @Override
    public String getConsoleCommand() {
        return "load json";
    }

    @Override
    public String getDescription() {
        return "Command for load application state from json file";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final Bootstrap bootstrap) {
        try (final InputStream inputStream = Files.newInputStream(Paths.get("data.json"))) {
            bootstrap.clearAllProjectAndTask();
            ObjectReader objectReader = new ObjectMapper().reader().forType(Domain.class);
            Domain domain = objectReader.readValue(inputStream);
            bootstrap.getProjectService().addAllProject(domain.getProjects());
            bootstrap.getTaskService().addAllTask(domain.getTasks());
            bootstrap.getUserService().addAllUser(domain.getUsers());
            ConsoleHelper.printMessage("Loading from json file was successful");
        } catch (IOException e) {
            ConsoleHelper.printMessage("An error has occurred during loading from json file");
        }
    }
}
