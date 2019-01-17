package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class ProjectCreateCommand implements Command {

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
    public void execute(final Bootstrap bootstrap) {
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final Project project = new Project();
        project.setName(projectName);
        project.setUserId(bootstrap.getActiveUser().getId());
        bootstrap.getProjectService().createOrUpdateProject(project);
        ConsoleHelper.printMessage(String.format("Project %s has been added", project.getName()));
    }
}
