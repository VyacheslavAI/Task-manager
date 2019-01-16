package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.util.CommandHelper;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class TaskReadCommand implements Command {

    @Override
    public void execute(final Controller controller) {
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = controller.getProjectService().loadProjectByName(projectName);
        controller.filterDataForActiveUser(projects);
        Project selectedProject = CommandHelper.selectProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.printMessage(String.format("Project %s not found", projectName));
            return;
        }

        ConsoleHelper.printMessage("Enter task name:");
        final String taskName = ConsoleHelper.readString();
        final List<Task> tasks = controller.getTaskService().loadTasksByProject(selectedProject);
        controller.filterDataForActiveUser(tasks);

        Task taskForRead = null;
        for (final Task persistentTask : tasks) {
            if (persistentTask.getName().equals(taskName)) {
                taskForRead = persistentTask;
            }
        }

        if (taskForRead == null) {
            ConsoleHelper.printMessage(String.format("Task with name %s in the project %s not found",
                    taskName, selectedProject.getName()));
            return;
        }

        ConsoleHelper.printMessage(String.format("Id: %s %nProject id: %s %nName: %s %nDate of creation: %s",
                taskForRead.getId(),
                taskForRead.getProjectId(),
                taskForRead.getName(),
                taskForRead.getCreated()));
    }
}
