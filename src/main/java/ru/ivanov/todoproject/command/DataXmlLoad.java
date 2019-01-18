package ru.ivanov.todoproject.command;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.dto.Domain;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataXmlLoad extends Command {

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
        try (InputStream inputStream = Files.newInputStream(Paths.get("data.xml"))) {
            serviceLocator.getProjectService().deleteAllProject();
            serviceLocator.getTaskService().deleteAllTask();
            XmlMapper xmlMapper = new XmlMapper();
            Domain domain = xmlMapper.readValue(inputStream, Domain.class);
            domain.loadFromDomain(serviceLocator);
            ConsoleHelper.printMessage("Loading from xml file was successful");
        } catch (IOException e) {
            ConsoleHelper.printMessage("An error has occurred during loading from xml file");
        }
    }
}
