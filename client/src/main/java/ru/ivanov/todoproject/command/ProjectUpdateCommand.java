package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.Date;
import java.util.List;

public class ProjectUpdateCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "edit project";
    }

    @Override
    public String getDescription() {
        return "Command to edit project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() {
        IProjectSOAPEndpoint projectSOAPEndpoint = serviceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        final Session session = serviceLocator.getSession();
        ConsoleHelper.print("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = projectSOAPEndpoint.readProject(session, projectName);
        final Project selectedProject = tryFindProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.print(String.format("Project %s not found", projectName));
            return;
        }

        ConsoleHelper.print("Please enter new name:");
        final String newName = ConsoleHelper.readString();
        ConsoleHelper.print("Please enter new date of creation(example: 04/01/1993):");
        final String date = ConsoleHelper.readString();
        final Date newDate = ConsoleHelper.parseDate(date);
        selectedProject.setName(newName);
        selectedProject.setCreated(ConsoleHelper.convertDateToXMLCalendar(newDate));

        projectSOAPEndpoint.updateProject(session, selectedProject);
        ConsoleHelper.print(String.format("Project %s has been updated", selectedProject.getName()));
    }
}
