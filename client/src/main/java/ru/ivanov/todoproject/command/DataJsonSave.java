package ru.ivanov.todoproject.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.dto.Domain;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataJsonSave extends Command {

    @Override
    public String getConsoleCommand() {
        return "save json";
    }

    @Override
    public String getDescription() {
        return "Command for save application state in json file";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        try (final OutputStream outputStream = Files.newOutputStream(Paths.get("data.json"))) {
            final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
            final Domain domain = Domain.createDomain(serviceLocator);
            objectWriter.writeValue(outputStream, domain);
            ConsoleHelper.printMessage("Saving in json file was successful");
        } catch (Exception e) {
            ConsoleHelper.printMessage("An error has occurred during saving in json format");
        }
    }
}
