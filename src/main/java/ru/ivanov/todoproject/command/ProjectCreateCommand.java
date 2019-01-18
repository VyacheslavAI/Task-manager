package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
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
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final Project project = new Project();
        project.setName(projectName);
        project.setUserId(serviceLocator.getUserService().getActiveUser().getId());
        serviceLocator.getProjectService().createOrUpdateProject(project);
        ConsoleHelper.printMessage(String.format("Project %s has been added", project.getName()));
    }
}
