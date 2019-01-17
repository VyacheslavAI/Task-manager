package ru.ivanov.todoproject.command;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.util.ConsoleHelper;
import ru.ivanov.todoproject.dto.Domain;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataXmlLoad implements Command {

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
    public void execute(Bootstrap bootstrap) {
        try (InputStream inputStream = Files.newInputStream(Paths.get("data.xml"))) {
            bootstrap.clearAllProjectAndTask();
            XmlMapper xmlMapper = new XmlMapper();
            Domain domain = xmlMapper.readValue(inputStream, Domain.class);
            bootstrap.getProjectService().addAllProject(domain.getProjects());
            bootstrap.getTaskService().addAllTask(domain.getTasks());
            bootstrap.getUserService().addAllUser(domain.getUsers());
            ConsoleHelper.printMessage("Loading from xml file was successful");
        } catch (IOException e) {
            ConsoleHelper.printMessage("An error has occurred during loading from xml file");
        }
    }
}
