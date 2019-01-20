package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.IUserSOAPEndpoint;
import ru.ivanov.todoproject.api.Project;
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
    public void execute(final SOAPServiceLocator soapServiceLocator) {
        final IProjectSOAPEndpoint projectSOAPEndpoint = soapServiceLocator.getProjectSOAPEndpointService().getProjectSOAPEndpointPort();
        final IUserSOAPEndpoint userSOAPEndpoint = soapServiceLocator.getUserSOAPEndpointService().getUserSOAPEndpointPort();
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final Project project = new Project();
        project.setName(projectName);
        project.setUserId(userSOAPEndpoint.getActiveUser().getId());
        projectSOAPEndpoint.createProject(project);
        ConsoleHelper.printMessage(String.format("Project %s has been added", project.getName()));
    }
}
