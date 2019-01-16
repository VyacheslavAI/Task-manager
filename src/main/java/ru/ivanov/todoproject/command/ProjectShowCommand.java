package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class ProjectShowCommand implements Command {

    @Override
    public void execute(final Controller controller) {
        ConsoleHelper.printMessage("All available projects: \r\n");
        final List<Project> projects = controller.getProjectService().loadAllProject();
        for (final Project project : projects) {
            ConsoleHelper.printMessage(String.format("Id: %s %n Name: %s %n Date of creation: %s",
                    project.getId(),
                    project.getName(),
                    project.getCreated()));

            ConsoleHelper.printDelimiter();
        }
    }
}
