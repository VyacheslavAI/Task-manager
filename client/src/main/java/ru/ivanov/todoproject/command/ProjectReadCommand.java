package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class ProjectReadCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "read project";
    }

    @Override
    public String getDescription() {
        return "Command to print information about available projects";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        IProjectSOAPEndpoint projectSOAPEndpoint = serviceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        final Session session = serviceLocator.getSession();
        ConsoleHelper.print("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = projectSOAPEndpoint.readProject(session, projectName);
        final Project selectedProject = tryFindProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.print(String.format("Project %s not found", projectName));
            return;
        }

        ConsoleHelper.print(String.format("Id: %s %nName: %s %nDate of creation: %s",
                selectedProject.getId(),
                selectedProject.getName(),
                ConsoleHelper.formatDate(selectedProject.getCreated())));
    }
}
