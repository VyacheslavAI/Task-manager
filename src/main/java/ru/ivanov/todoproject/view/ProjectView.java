package ru.ivanov.todoproject.view;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.List;

public class ProjectView {

    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void showAndSelectProjects(List<Project> projects) {
        ConsoleHelper.printDelimiter();

        if (projects.isEmpty()) {
            ConsoleHelper.printMessage("Projects list is empty");
            controller.goToMainMenu();
        }

        ConsoleHelper.printMessage("Enter project number:");
        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            ConsoleHelper.printMessage(String.format("\t %d: %s %s", i,
                    project.getName(), project.getCreated().toString()));
        }
        int projectNumber = ConsoleHelper.readInt(0, projects.size() - 1);
        controller.goToEditProjectForm(projects.get(projectNumber));
    }
}