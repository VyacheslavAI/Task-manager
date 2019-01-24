package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class TaskShowByProjectCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "show task";
    }

    @Override
    public String getDescription() {
        return "Command to print all available tasks for project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        ITaskSOAPEndpoint taskSOAPEndpoint = serviceLocator.getTaskSOAPEndpointService().getTaskSOAPEndpointPort();
        IProjectSOAPEndpoint projectSOAPEndpoint = serviceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        final Session session = serviceLocator.getSession();
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = projectSOAPEndpoint.readProject(session, projectName);
        final Project selectedProject = tryFindProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.printMessage(String.format("Project %s not found", projectName));
            return;
        }

        final List<Task> tasks = taskSOAPEndpoint.getTasksByProject(session, selectedProject);
        for (final Task persistentTask : tasks) {
            ConsoleHelper.printMessage(String.format("Id: %s %n Project id: %s %n Name: %s %n Date of creation: %s",
                    persistentTask.getId(),
                    persistentTask.getProjectId(),
                    persistentTask.getName(),
                    persistentTask.getCreated()));

            ConsoleHelper.printDelimiter();
        }
    }
}
