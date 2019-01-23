package ru.ivanov.todoproject.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.dto.Domain;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataJsonLoad extends Command {

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
    public void execute(final ServiceLocator serviceLocator) {
        try (final InputStream inputStream = Files.newInputStream(Paths.get("data.json"))) {
            serviceLocator.getProjectService().deleteAllProject();
            serviceLocator.getTaskService().deleteAllTask();
            final ObjectReader objectReader = new ObjectMapper().reader().forType(Domain.class);
            final Domain domain = objectReader.readValue(inputStream);
            domain.loadFromDomain(serviceLocator);
            ConsoleHelper.printMessage("Loading from json file was successful");
        } catch (IOException e) {
            ConsoleHelper.printMessage("An error has occurred during loading from json file");
        }
    }
}
