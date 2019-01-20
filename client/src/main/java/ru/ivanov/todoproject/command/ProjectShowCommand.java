package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class ProjectShowCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "show project";
    }

    @Override
    public String getDescription() {
        return "Command for print all available projects";
    }

    @Override
    public void execute(final SOAPServiceLocator soapServiceLocator) {
        IProjectSOAPEndpoint projectSOAPEndpoint = soapServiceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        ConsoleHelper.printMessage("All available projects: \r\n");
        final List<Project> projects = projectSOAPEndpoint.showProjects();
        for (final Project project : projects) {
            ConsoleHelper.printMessage(String.format("Id: %s %n Name: %s %n Date of creation: %s",
                    project.getId(),
                    project.getName(),
                    project.getCreated()));
            ConsoleHelper.printDelimiter();
        }
    }
}
