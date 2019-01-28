package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.dto.Domain;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataBinaryLoad extends AbstractCommand {

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
    public void execute(final ServiceLocator serviceLocator) {
        try (final InputStream inputStream = Files.newInputStream(Paths.get("data.bin"));
             final ObjectInput objectInput = new ObjectInputStream(inputStream)) {
            serviceLocator.getProjectService().deleteAllProject();
            serviceLocator.getTaskService().deleteAllTask();
            final Domain domain = (Domain) objectInput.readObject();
            domain.loadFromDomain(serviceLocator);
            ConsoleHelper.print("Loading from binary file was successful");
        } catch (Exception e) {
            ConsoleHelper.print("An error has occurred during loading from binary file");
        }
    }
}
