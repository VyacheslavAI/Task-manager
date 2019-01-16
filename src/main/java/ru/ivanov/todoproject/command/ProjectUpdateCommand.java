package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.util.CommandHelper;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class ProjectUpdateCommand implements Command {

    @Override
    public void execute(final Controller controller) {
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = controller.getProjectService().loadProjectByName(projectName);
        controller.filterDataForActiveUser(projects);
        final Project selectedProject = CommandHelper.selectProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.printMessage(String.format("Project %s not found", projectName));
            return;
        }

        CommandHelper.updateProject(selectedProject);
        controller.getProjectService().createOrUpdateProject(selectedProject);
        ConsoleHelper.printMessage(String.format("Project %s has been updated", selectedProject.getName()));
    }
}
