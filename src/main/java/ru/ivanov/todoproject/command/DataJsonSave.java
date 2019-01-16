package ru.ivanov.todoproject.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.ivanov.todoproject.util.Domain;
import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataJsonSave implements Command {

    @Override
    public void execute(final Controller controller) {
        try (final OutputStream outputStream = Files.newOutputStream(Paths.get("data.json"))) {
            final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
            final Domain domain = Domain.createDomain(controller);
            objectWriter.writeValue(outputStream, domain);
            ConsoleHelper.printMessage("Convert to json successfully");
        } catch (Exception e) {
            ConsoleHelper.printMessage("An error has occurred during transform to json");
        }
    }
}
