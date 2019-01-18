package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
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
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = serviceLocator.getProjectService().loadProjectByName(projectName);
        serviceLocator.getUserService().filterProjectsForActiveUser(projects);
        final Project selectedProject = tryFindProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.printMessage(String.format("Project %s not found", projectName));
            return;
        }

        final List<Task> tasks = serviceLocator.getTaskService().loadTasksByProject(selectedProject);
        serviceLocator.getUserService().filterTasksForActiveUser(tasks);
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
