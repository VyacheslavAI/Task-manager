package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
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
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = serviceLocator.getProjectService().loadProjectByName(projectName);
        serviceLocator.getUserService().filterProjectsForUser(projects);
        final Project selectedProject = tryFindProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.printMessage(String.format("Project %s not found", projectName));
            return;
        }

        ConsoleHelper.printMessage("Enter task name:");
        final String taskName = ConsoleHelper.readString();
        final List<Task> projectTask = serviceLocator.getTaskService().loadTasksByProject(selectedProject);
        serviceLocator.getUserService().filterTasksForUser(projectTask);

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

        serviceLocator.getTaskService().deleteTask(taskForDelete);
        ConsoleHelper.printMessage(String.format("Task %s has been deleted", taskName));
    }
}
