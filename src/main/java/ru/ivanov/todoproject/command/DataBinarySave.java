package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.util.Domain;
import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataBinarySave implements Command {

    @Override
    public void execute(Controller controller) {
        try (final ObjectOutput objectOutput = new ObjectOutputStream(Files.newOutputStream(Paths.get("data.bin")))) {
            Domain domain = Domain.createDomain(controller);
            objectOutput.writeObject(domain);
            ConsoleHelper.printMessage("Serialization successful");
        } catch (IOException e) {
            ConsoleHelper.printMessage("An error has occurred during serialization");
        }
    }
}
