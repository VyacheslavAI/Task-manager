package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.util.CommandHelper;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class ProjectUpdateCommand implements Command {

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
    public void execute(final Bootstrap bootstrap) {
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = bootstrap.getProjectService().loadProjectByName(projectName);
        bootstrap.filterProjectsForActiveUser(projects);
        final Project selectedProject = CommandHelper.selectProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.printMessage(String.format("Project %s not found", projectName));
            return;
        }

        CommandHelper.updateProject(selectedProject);
        bootstrap.getProjectService().createOrUpdateProject(selectedProject);
        ConsoleHelper.printMessage(String.format("Project %s has been updated", selectedProject.getName()));
    }
}
