package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.ITaskSOAPEndpoint;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.Task;
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
    public void execute(final SOAPServiceLocator soapServiceLocator) {
        ITaskSOAPEndpoint taskSOAPEndpoint = soapServiceLocator.getTaskSOAPEndpointService().getTaskSOAPEndpointPort();
        IProjectSOAPEndpoint projectSOAPEndpoint = soapServiceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = projectSOAPEndpoint.readProject(projectName);
        final Project selectedProject = tryFindProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.printMessage(String.format("Project %s not found", projectName));
            return;
        }

        final List<Task> tasks = taskSOAPEndpoint.getTasksByProject(selectedProject);
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
