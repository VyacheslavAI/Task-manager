package ru.ivanov.todoproject.command;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.dto.Domain;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataXmlLoad extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "load xml";
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
        try (final InputStream inputStream = Files.newInputStream(Paths.get("data.xml"))) {
            serviceLocator.getProjectService().deleteAllProject();
            serviceLocator.getTaskService().deleteAllTask();
            final XmlMapper xmlMapper = new XmlMapper();
            final Domain domain = xmlMapper.readValue(inputStream, Domain.class);
            domain.loadFromDomain(serviceLocator);
            ConsoleHelper.print("Loading from xml file was successful");
        } catch (IOException e) {
            ConsoleHelper.print("An error has occurred during loading from xml file");
        }
    }
}
