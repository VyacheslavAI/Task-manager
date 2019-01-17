package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class ProjectShowCommand implements Command {

    @Override
    public String getConsoleCommand() {
        return "show project";
    }

    @Override
    public String getDescription() {
        return "Command for print all available projects";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute(final Bootstrap bootstrap) {
        ConsoleHelper.printMessage("All available projects: \r\n");
        final List<Project> projects = bootstrap.getProjectService().loadAllProject();
        bootstrap.filterProjectsForActiveUser(projects);
        for (final Project project : projects) {
            ConsoleHelper.printMessage(String.format("Id: %s %n Name: %s %n Date of creation: %s",
                    project.getId(),
                    project.getName(),
                    project.getCreated()));

            ConsoleHelper.printDelimiter();
        }
    }
}
