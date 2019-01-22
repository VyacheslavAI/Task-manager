package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class TaskDeleteCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "delete task";
    }

    @Override
    public String getDescription() {
        return "Command to delete task";
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

        ConsoleHelper.printMessage("Enter task name:");
        final String taskName = ConsoleHelper.readString();
        final List<Task> projectTask = taskSOAPEndpoint.getTasksByProject(session, selectedProject);

        Task taskForDelete = null;
        for (final Task persistentTask : projectTask) {
            if (persistentTask.getName().equals(taskName)) {
                taskForDelete = persistentTask;
            }
        }

        if (taskForDelete == null) {
            ConsoleHelper.printMessage(String.format("Task with the name %s in the project %s not found",
                    taskName, selectedProject.getName()));
        }

        taskSOAPEndpoint.deleteTask(session, taskForDelete);
        ConsoleHelper.printMessage(String.format("Task %s has been deleted", taskName));
    }
}
