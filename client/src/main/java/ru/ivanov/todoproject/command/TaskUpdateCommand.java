package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.ITaskSOAPEndpoint;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.Task;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.Date;
import java.util.List;

public class TaskUpdateCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "edit task";
    }

    @Override
    public String getDescription() {
        return "Command to edit task for select project";
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

        ConsoleHelper.printMessage("Enter task name:");
        final String taskName = ConsoleHelper.readString();
        final List<Task> tasks = taskSOAPEndpoint.getTasksByProject(selectedProject);

        Task taskForUpdate = null;
        for (final Task persistentTask : tasks) {
            if (persistentTask.getName().equals(taskName)) {
                taskForUpdate = persistentTask;
            }
        }

        if (taskForUpdate == null) {
            ConsoleHelper.printMessage(String.format("Task with name %s in the project %s not found",
                    taskName, selectedProject.getName()));
            return;
        }

        ConsoleHelper.printMessage("Please enter new name:");
        final String newName = ConsoleHelper.readString();
        ConsoleHelper.printMessage("Please enter new date of creation(example: 04/01/1993):");
        final String date = ConsoleHelper.readString();
        final Date newDate = ConsoleHelper.parseDate(date);
        taskForUpdate.setName(newName);
        taskForUpdate.setCreated(ConsoleHelper.convertDateToXMLCalendar(newDate));

        ConsoleHelper.printMessage(String.format("Task %s has been updated", taskName));
    }
}
