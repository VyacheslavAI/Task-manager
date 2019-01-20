package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class ProjectReadCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "read project";
    }

    @Override
    public String getDescription() {
        return "Command to print information about available projects";
    }

    @Override
    public void execute(final SOAPServiceLocator soapServiceLocator) {
        IProjectSOAPEndpoint projectSOAPEndpoint = soapServiceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = projectSOAPEndpoint.readProject(projectName);
        final Project selectedProject = tryFindProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.printMessage(String.format("Project %s not found", projectName));
            return;
        }

        ConsoleHelper.printMessage(String.format("Id: %s %nName: %s %nDate of creation: %s",
                selectedProject.getId(),
                selectedProject.getName(),
                ConsoleHelper.formatDate(selectedProject.getCreated())));
    }
}
