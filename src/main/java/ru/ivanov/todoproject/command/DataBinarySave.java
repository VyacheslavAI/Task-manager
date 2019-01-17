package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.util.ConsoleHelper;
import ru.ivanov.todoproject.dto.Domain;

import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataBinarySave implements Command {

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
    public void execute(Bootstrap bootstrap) {
        try (final OutputStream outputStream = Files.newOutputStream(Paths.get("data.bin"));
             final ObjectOutput objectOutput = new ObjectOutputStream(outputStream)) {
            Domain domain = Domain.createDomain(bootstrap);
            objectOutput.writeObject(domain);
            ConsoleHelper.printMessage("Saving in binary file was successful");
        } catch (Exception e) {
            ConsoleHelper.printMessage("An error has occurred during saving in binary format");
        }
    }
}
