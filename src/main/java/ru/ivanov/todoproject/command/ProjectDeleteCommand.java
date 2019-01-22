package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class ProjectDeleteCommand extends Command {

    @Override
    public String getConsoleCommand() {
        return "delete project";
    }

    @Override
    public String getDescription() {
        return "Command for delete project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final ServiceLocator serviceLocator) {
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = serviceLocator.getProjectService().loadProjectByName(projectName);
        serviceLocator.getUserService().filterProjectsForUser(projects);
        final Project selectedProject = tryFindProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.printMessage(String.format("Project %s not found", projectName));
            return;
        }

        serviceLocator.getProjectService().deleteProject(selectedProject);
        ConsoleHelper.printMessage(String.format("Project %s has been deleted", selectedProject.getName()));
    }
}
