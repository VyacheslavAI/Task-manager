package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.ITaskSOAPEndpoint;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.Task;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class TaskReadCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "read task";
    }

    @Override
    public String getDescription() {
        return "Command to print information about available tasks for project";
    }

    @Override
    public void execute(final SOAPServiceLocator soapServiceLocator) {
        ITaskSOAPEndpoint taskSOAPEndpoint = soapServiceLocator.getTaskSOAPEndpointService().getTaskSOAPEndpointPort();
        IProjectSOAPEndpoint projectSOAPEndpoint = soapServiceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = projectSOAPEndpoint.readProject(projectName);
        Project selectedProject = tryFindProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.printMessage(String.format("Project %s not found", projectName));
            return;
        }

        ConsoleHelper.printMessage("Enter task name:");
        final String taskName = ConsoleHelper.readString();
        final List<Task> tasks = taskSOAPEndpoint.getTasksByProject(selectedProject);

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

        final String format = "Id: %s %nProject id: %s %nName: %s %nDate of creation: %s";
        ConsoleHelper.printMessage(String.format(format,
                taskForRead.getId(),
                taskForRead.getProjectId(),
                taskForRead.getName(),
                taskForRead.getCreated()));
    }
}
