package ru.ivanov.todoproject.util;

import ru.ivanov.todoproject.entity.AbstractEntity;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;

import java.util.Date;
import java.util.List;

public class CommandHelper<E extends AbstractEntity> {

    private CommandHelper() {
    }

    public static Project selectProject(final List<Project> projects) {
        if (projects == null || projects.isEmpty())
            return null;

        if (projects.size() == 1)
            return projects.get(0);

        ConsoleHelper.printMessage("Several projects found with data name. \n" +
                "Select the creation date of the desired project.");
        for (int i = 0; i < projects.size(); i++) {
            final Date created = projects.get(i).getCreated();
            ConsoleHelper.printMessage(String.format("%d %s", i, ConsoleHelper.formatDate(created)));
        }

        final int firstIndex = 0;
        final int lastIndex = projects.size() - 1;
        final int indexOfProject = ConsoleHelper.readInt(firstIndex, lastIndex);
        return projects.get(indexOfProject);
    }

    public static void updateProject(final Project project) {
        ConsoleHelper.printMessage("Please enter new name:");
        final String newName = ConsoleHelper.readString();
        ConsoleHelper.printMessage("Please enter new date of create(example: 04/01/1993):");
        final Date newDate = ConsoleHelper.parseDate(ConsoleHelper.readString());
        project.setName(newName);
        project.setCreated(newDate);
    }

    public static void updateTask(final Task task) {
        ConsoleHelper.printMessage("Please enter new name:");
        final String newName = ConsoleHelper.readString();
        ConsoleHelper.printMessage("Please enter new date of create(example: 04/01/1993):");
        final Date newDate = ConsoleHelper.parseDate(ConsoleHelper.readString());
        task.setName(newName);
        task.setCreated(newDate);
    }
}