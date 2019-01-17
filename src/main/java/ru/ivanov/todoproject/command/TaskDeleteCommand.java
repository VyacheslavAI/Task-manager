package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.util.CommandHelper;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class TaskDeleteCommand implements Command {

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
    public void execute(final Bootstrap bootstrap) {
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = bootstrap.getProjectService().loadProjectByName(projectName);
        bootstrap.filterProjectsForActiveUser(projects);
        final Project selectedProject = CommandHelper.selectProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.printMessage(String.format("Project %s not found", projectName));
            return;
        }

        ConsoleHelper.printMessage("Enter task name:");
        final String taskName = ConsoleHelper.readString();
        final List<Task> projectTask = bootstrap.getTaskService().loadTasksByProject(selectedProject);
        bootstrap.filterTasksForActiveUser(projectTask);

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

        bootstrap.getTaskService().deleteTask(taskForDelete);
        ConsoleHelper.printMessage(String.format("Task %s has been deleted", taskName));
    }
}
