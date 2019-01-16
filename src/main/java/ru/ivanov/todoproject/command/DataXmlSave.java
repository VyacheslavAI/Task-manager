package ru.ivanov.todoproject.command;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.util.ConsoleHelper;
import ru.ivanov.todoproject.util.Domain;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataXmlSave implements Command {

    @Override
    public void execute(Controller controller) {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get("data.xml"))) {
            Domain domain = Domain.createDomain(controller);
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(outputStream, domain);
            ConsoleHelper.printMessage("Convert to xml successfully");
        } catch (IOException e) {
            ConsoleHelper.printMessage("An error has occurred during transform to xml");
        }
    }
}
