package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class ProjectDeleteCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "delete project";
    }

    @Override
    public String getDescription() {
        return "Command for delete project";
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

        projectSOAPEndpoint.deleteProject(selectedProject);
        ConsoleHelper.printMessage(String.format("Project %s has been deleted", selectedProject.getName()));
    }
}
