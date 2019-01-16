package ru.ivanov.todoproject.command;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.util.ConsoleHelper;
import ru.ivanov.todoproject.util.Domain;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataXmlLoad implements Command {

    @Override
    public void execute(Controller controller) {
        try (InputStream inputStream = Files.newInputStream(Paths.get("data.xml"))) {
            controller.clearAllEntity();
            XmlMapper xmlMapper = new XmlMapper();
            Domain domain = xmlMapper.readValue(inputStream, Domain.class);
            controller.getProjectService().addAllProject(domain.getProjects());
            controller.getTaskService().addAllTask(domain.getTasks());
            ConsoleHelper.printMessage("Convert from xml successfully");
        } catch (IOException e) {
            ConsoleHelper.printMessage("An error has occurred during read xml");
        }
    }
}
