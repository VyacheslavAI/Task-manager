package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class TaskCreateCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "create task";
    }

    @Override
    public String getDescription() {
        return "Command to create task";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        ITaskSOAPEndpoint taskSOAPEndpoint = serviceLocator.getTaskSOAPEndpointService().getTaskSOAPEndpointPort();
        IProjectSOAPEndpoint projectSOAPEndpoint = serviceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        IUserSOAPEndpoint userSOAPEndpoint = serviceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort();
        final Session session = serviceLocator.getSession();
        ConsoleHelper.print("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = projectSOAPEndpoint.readProject(session, projectName);
        final Project selectedProject = tryFindProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.print(String.format("Project %s not found", projectName));
            return;
        }

        ConsoleHelper.print("Enter task name:");
        final String taskName = ConsoleHelper.readString();
        final Task task = new Task();
        final User user = serviceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort().getUser(session);
        task.setName(taskName);
        task.setProjectId(selectedProject.getId());
        task.setUserId(user.getId());
        taskSOAPEndpoint.createTask(session, task);
        ConsoleHelper.print(String.format("Task %s has been added", taskName));
    }
}
