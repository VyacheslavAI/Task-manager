package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class ProjectCreateCommand implements Command {

    @Override
    public void execute(final Controller controller) {
        ConsoleHelper.printMessage("Enter project name:");
        final String projectName = ConsoleHelper.readString();
        final Project project = new Project();
        project.setName(projectName);
        controller.getProjectService().createOrUpdateProject(project);
        ConsoleHelper.printMessage(String.format("Project %s has been added", project.getName()));
    }
}
