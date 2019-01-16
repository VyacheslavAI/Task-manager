package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.util.CommandHelper;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class ProjectReadCommand implements Command {

    @Override
    public void execute(final Controller controller) {
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final List<Project> projects = controller.getProjectService().loadProjectByName(projectName);
        controller.getUserService().filterDataForActiveUser(projects, controller.getActiveUser());
        final Project selectedProject = CommandHelper.selectProject(projects);

        if (selectedProject == null) {
            ConsoleHelper.printMessage(String.format("Project %s not found", projectName));
            return;
        }

        ConsoleHelper.printMessage(String.format("Id: %s %nName: %s %nDate of creation: %s",
                selectedProject.getId(),
                selectedProject.getName(),
                ConsoleHelper.formatDate(selectedProject.getCreated())));
    }
}
