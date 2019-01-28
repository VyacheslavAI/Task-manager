package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class ProjectShowCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "show project";
    }

    @Override
    public String getDescription() {
        return "Command for print all available projects";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        IProjectSOAPEndpoint projectSOAPEndpoint = serviceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        final Session session = serviceLocator.getSession();
        ConsoleHelper.print("All available projects: \r\n");
        final List<Project> projects = projectSOAPEndpoint.showProjects(session);
        for (final Project project : projects) {
            ConsoleHelper.print(String.format("Id: %s %n Name: %s %n Date of creation: %s",
                    project.getId(),
                    project.getName(),
                    project.getCreated()));
            ConsoleHelper.printDelimiter();
        }
    }
}
