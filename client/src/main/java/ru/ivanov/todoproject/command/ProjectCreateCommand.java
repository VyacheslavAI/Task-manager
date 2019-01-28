package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.api.User;
import ru.ivanov.todoproject.util.ConsoleHelper;

import static ru.ivanov.todoproject.util.ConsoleHelper.readString;

public class ProjectCreateCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "create project";
    }

    @Override
    public String getDescription() {
        return "Create new project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() {
        final IProjectSOAPEndpoint projectSOAPEndpoint = serviceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        final Session session = serviceLocator.getUserData().getSession();
        ConsoleHelper.print("Enter project name:");
        final String projectName = readString();
        final User user = serviceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort().getUser(session);
        final Project project = new Project();
        project.setName(projectName);
        project.setUserId(user.getId());
        projectSOAPEndpoint.createProject(session, project);
        ConsoleHelper.print(String.format("Project %s has been added", project.getName()));
    }
}
