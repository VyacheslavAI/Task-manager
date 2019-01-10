package ru.ivanov.todoproject.view;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskEditView {

    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void editTaskForm(Task task) {
        ConsoleHelper.printDelimiter();
        ConsoleHelper.printMessage("Id: " + task.getId());
        ConsoleHelper.printMessage("Name: " + task.getName());
        ConsoleHelper.printMessage("Date of creation: " + task.getCreated().toString());
        ConsoleHelper.printMessage("Choose section number:");
        ConsoleHelper.printMessage("1: Edit task information");
        ConsoleHelper.printMessage("2: Delete task");
        ConsoleHelper.printMessage("3: Return to main menu");

        int sectionNumber = ConsoleHelper.readInt(1, 3);

        switch (sectionNumber) {
            case 1:
                editTaskData(task);
                break;
            case 2:
                deleteTaskForm(task);
                break;
            case 3:
                controller.goToMainMenu();
        }
    }

    public void editTaskData(Task task) {
        int sectionNumber = 0;

        String taskName = task.getName();
        Date taskCreation = task.getCreated();

        ConsoleHelper.printDelimiter();
        while (sectionNumber != 3) {
            ConsoleHelper.printMessage("Enter edit field number");
            ConsoleHelper.printMessage("1: Name");
            ConsoleHelper.printMessage("2: Date of creation");
            ConsoleHelper.printMessage("3: Finish editing");
            sectionNumber = ConsoleHelper.readInt(1, 3);
            switch (sectionNumber) {
                case 1: {
                    ConsoleHelper.printMessage("Enter new task name:");
                    taskName = ConsoleHelper.readString();
                    break;
                }
                case 2: {
                    ConsoleHelper.printMessage("Enter new date of creation in format dd/MM/yyyy (example 14/07/1993):");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        taskCreation = simpleDateFormat.parse(ConsoleHelper.readString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        controller.changeTaskData(task.getId(), task.getProjectId(), taskName, taskCreation);
        ConsoleHelper.printMessage("Task has been updated");
        controller.goToEditTaskForm(task);
    }

    public void deleteTaskForm(Task task) {
        controller.deleteTask(task);
        ConsoleHelper.printMessage("Task has been deleted");
        controller.goToMainMenu();
    }

    public void addTaskForm(Project project) {
        ConsoleHelper.printDelimiter();
        ConsoleHelper.printMessage("Enter task name:");
        String name = ConsoleHelper.readString();
        controller.changeTaskData("0", project.getId(), name, new Date());
        ConsoleHelper.printMessage("Task has been added");
        controller.goToEditProjectForm(project);
    }
}
