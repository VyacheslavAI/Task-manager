package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.dto.Domain;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataBinarySave extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "save bin";
    }

    @Override
    public String getDescription() {
        return "Command for save application state in binary file";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        try (final OutputStream outputStream = Files.newOutputStream(Paths.get("data.bin"));
             final ObjectOutput objectOutput = new ObjectOutputStream(outputStream)) {
            final Domain domain = Domain.createDomain(serviceLocator);
            objectOutput.writeObject(domain);
            ConsoleHelper.print("Saving in binary file was successful");
        } catch (Exception e) {
            ConsoleHelper.print("An error has occurred during saving in binary format");
        }
    }
}
