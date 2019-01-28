package ru.ivanov.todoproject.command;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataXmlSave extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "save xml";
    }

    @Override
    public String getDescription() {
        return "Command to save application state in xml file";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        try (final OutputStream outputStream = Files.newOutputStream(Paths.get("data.xml"))) {
            final Domain domain = Domain.createDomain(serviceLocator);
            final XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(outputStream, domain);
            ConsoleHelper.print("Saving in xml file was successful");
        } catch (IOException e) {
            ConsoleHelper.print("An error has occurred during saving in xml format");
        }
    }
}
