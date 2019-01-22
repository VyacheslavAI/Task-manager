package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public abstract class Command {

    private static final String warningString = "Several projects found with data name. \n" +
            "Select the creation date of the desired project.";

    Project tryFindProject(final List<Project> projects) {
        if (projects == null || projects.isEmpty()) return null;
        if (projects.size() == 1) return projects.get(0);

        ConsoleHelper.printMessage(warningString);
        for (int i = 0; i < projects.size(); i++) {
            final Project project = projects.get(i);
            final String created = ConsoleHelper.formatDate(project.getCreated());
            final String indexAndDate = String.format("%d %s", i, created);
            ConsoleHelper.printMessage(indexAndDate);
        }

        final int firstIndex = 0;
        final int lastIndex = projects.size() - 1;
        final int indexOfProject = ConsoleHelper.readInt(firstIndex, lastIndex);
        return projects.get(indexOfProject);
    }

    public abstract String getConsoleCommand();

    public abstract String getDescription();

    public abstract void execute(final ServiceLocator serviceLocator);
}
