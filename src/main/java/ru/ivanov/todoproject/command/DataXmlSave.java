package ru.ivanov.todoproject.command;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.dto.Domain;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataXmlSave extends Command {

    @Override
    public String getConsoleCommand() {
        return "save xml";
    }

    @Override
    public String getDescription() {
        return "Command for save application state in xml file";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get("data.xml"))) {
            Domain domain = Domain.createDomain(serviceLocator);
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(outputStream, domain);
            ConsoleHelper.printMessage("Saving in xml file was successful");
        } catch (IOException e) {
            ConsoleHelper.printMessage("An error has occurred during saving in xml format");
        }
    }
}
