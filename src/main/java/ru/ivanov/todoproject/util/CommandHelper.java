package ru.ivanov.todoproject.util;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

import java.util.Date;
import java.util.List;

public final class CommandHelper {

    private static final String warningString = "Several projects found with data name. \n" +
            "Select the creation date of the desired project.";

    private CommandHelper() {
    }

    public static Project selectProject(final List<Project> projects) {
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

    public static void updateProject(final Project project) {
        ConsoleHelper.printMessage("Please enter new name:");
        final String newName = ConsoleHelper.readString();
        ConsoleHelper.printMessage("Please enter new date of creation(example: 04/01/1993):");
        final String date = ConsoleHelper.readString();
        final Date newDate = ConsoleHelper.parseDate(date);
        project.setName(newName);
        project.setCreated(newDate);
    }

    public static void updateTask(final Task task) {
        ConsoleHelper.printMessage("Please enter new name:");
        final String newName = ConsoleHelper.readString();
        ConsoleHelper.printMessage("Please enter new date of creation(example: 04/01/1993):");
        final String date = ConsoleHelper.readString();
        final Date newDate = ConsoleHelper.parseDate(date);
        task.setName(newName);
        task.setCreated(newDate);
    }

    public static void updateUser(final User user) {
        ConsoleHelper.printMessage("Please enter new login:");
        final String newLogin = ConsoleHelper.readString();
        ConsoleHelper.printMessage("Please enter new date of creation(example: 04/01/1993):");
        final String date = ConsoleHelper.readString();
        final Date newDate = ConsoleHelper.parseDate(date);
        ConsoleHelper.printMessage("Please enter new password:");
        final String newPassword = ConsoleHelper.readString();
        user.setLogin(newLogin);
        user.setCreated(newDate);
        user.setPassword(newPassword);
    }
}