package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class TaskReadCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "read task";
    }

    @Override
    public String getDescription() {
        return "Command to print information about available tasks for project";
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
        ConsoleHelper.print("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = projectSOAPEndpoint.readProject(session, projectName);
        Project selectedProject = tryFindProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.print(String.format("Project %s not found", projectName));
            return;
        }

        ConsoleHelper.print("Enter task name:");
        final String taskName = ConsoleHelper.readString();
        final List<Task> tasks = taskSOAPEndpoint.getTasksByProject(session, selectedProject);

        Task taskForRead = null;
        for (final Task persistentTask : tasks) {
            if (persistentTask.getName().equals(taskName)) {
                taskForRead = persistentTask;
            }
        }

        if (taskForRead == null) {
            ConsoleHelper.print(String.format("Task with name %s in the project %s not found",
                    taskName, selectedProject.getName()));
            return;
        }

        final String format = "Id: %s %nProject id: %s %nName: %s %nDate of creation: %s";
        ConsoleHelper.print(String.format(format,
                taskForRead.getId(),
                taskForRead.getProjectId(),
                taskForRead.getName(),
                taskForRead.getCreated()));
    }
}
