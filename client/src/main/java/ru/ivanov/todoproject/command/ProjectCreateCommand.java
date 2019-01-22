package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class ProjectCreateCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "create project";
    }

    @Override
    public String getDescription() {
        return "Command for create project";
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        final IProjectSOAPEndpoint projectSOAPEndpoint = serviceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        final Session session = serviceLocator.getSession();
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final User user = serviceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort().getUser(session);
        final Project project = new Project();
        project.setName(projectName);
        project.setUserId(user.getId());
        projectSOAPEndpoint.createProject(session, project);
        ConsoleHelper.printMessage(String.format("Project %s has been added", project.getName()));
    }
}
