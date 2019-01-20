package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.api.*;
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
    public void execute(final SOAPServiceLocator soapServiceLocator) {
        ITaskSOAPEndpoint taskSOAPEndpoint = soapServiceLocator.getTaskSOAPEndpointService().getTaskSOAPEndpointPort();
        IProjectSOAPEndpoint projectSOAPEndpoint = soapServiceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        IUserSOAPEndpoint userSOAPEndpoint = soapServiceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort();
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = projectSOAPEndpoint.readProject(projectName);
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
        task.setUserId(userSOAPEndpoint.getActiveUser().getId());
        taskSOAPEndpoint.createTask(task);
        ConsoleHelper.printMessage(String.format("Task %s has been added", taskName));
    }
}
