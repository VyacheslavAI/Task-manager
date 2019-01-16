package ru.ivanov.todoproject.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import ru.ivanov.todoproject.util.Domain;
import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataJsonLoad implements Command {

    @Override
    public void execute(final Controller controller) {
        try (final InputStream inputStream = Files.newInputStream(Paths.get("data.json"))) {
            controller.clearAllEntity();
            ObjectReader objectReader = new ObjectMapper().reader().forType(Domain.class);
            Domain domain = objectReader.readValue(inputStream);
            controller.getProjectService().addAllProject(domain.getProjects());
            controller.getTaskService().addAllTask(domain.getTasks());
            ConsoleHelper.printMessage("Loading from json was successful");
        } catch (IOException e) {
            ConsoleHelper.printMessage("An error has occurred during read json");
            System.out.println(e.getMessage());
        }
    }
}
