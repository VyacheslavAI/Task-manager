package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class TaskCreateCommand extends Command {

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
        final Task task = new Task();
        task.setName(taskName);
        task.setProjectId(selectedProject.getId());
        task.setUserId(serviceLocator.getUserService().getActiveUser().getId());
        serviceLocator.getTaskService().createOrUpdateTask(task);
        ConsoleHelper.printMessage(String.format("Task %s has been added", taskName));
    }
}
