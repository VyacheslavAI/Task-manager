package ru.ivanov.todoproject.view;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class MainView {

    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void showMainMenu() {
        ConsoleHelper.printDelimiter();
        ConsoleHelper.printMessage("Welcome to task manager application. \r\n" +
                "Enter section number");
        ConsoleHelper.printMessage("1: Add project");
        ConsoleHelper.printMessage("2: Projects");
        ConsoleHelper.printMessage("3: Tasks");
        ConsoleHelper.printMessage("4: Save");
        ConsoleHelper.printMessage("5: Load");
        ConsoleHelper.printMessage("6: Exit");
        int selectMenuItem = ConsoleHelper.readInt(1, 6);
        switch (selectMenuItem) {
            case 1:
                controller.addProject();
                break;
            case 2:
                controller.showProjects();
                break;
            case 3:
                controller.showTasks();
                break;
            case 4: {
                controller.saveState();
                controller.goToMainMenu();
                break;
            }
            case 5: {
                controller.loadState();
                controller.goToMainMenu();
                break;
            }
        }
    }
}
