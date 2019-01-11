package ru.ivanov.todoproject.view;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class TaskView {

    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void showAndSelectTask(final List<Task> tasks) {
        ConsoleHelper.printDelimiter();

        if (tasks.isEmpty()) {
            ConsoleHelper.printMessage("Project has no tasks");
            controller.goToMainMenu();
        }

        ConsoleHelper.printMessage("Enter task number:");
        for (int i = 0; i < tasks.size(); i++) {
            final Task task = tasks.get(i);
            ConsoleHelper.printMessage(String.format("\t %d %s %s", i,
                    task.getName(), task.getCreated().toString()));
        }
        final int taskNumber = ConsoleHelper.readInt(0, tasks.size() - 1);
        controller.goToEditTaskForm(tasks.get(taskNumber));
    }
}