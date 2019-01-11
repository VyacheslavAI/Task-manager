package ru.ivanov.todoproject.view;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectEditView {

    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void editProjectForm(final Project project) {
        ConsoleHelper.printDelimiter();
        ConsoleHelper.printMessage("Id: " + project.getId());
        ConsoleHelper.printMessage("Name: " + project.getName());
        ConsoleHelper.printMessage("Date of creation: " + project.getCreated().toString());
        ConsoleHelper.printMessage("Choose section number:");
        ConsoleHelper.printMessage("1: Edit project information");
        ConsoleHelper.printMessage("2: Project tasks");
        ConsoleHelper.printMessage("3: Add task to project");
        ConsoleHelper.printMessage("4: Delete project");
        ConsoleHelper.printMessage("5: Return to main menu");

        final int sectionNumber = ConsoleHelper.readInt(1, 5);

        switch (sectionNumber) {
            case 1:
                editProjectData(project);
                break;
            case 2:
                controller.showTasksForProject(project);
                break;
            case 3:
                controller.addTaskForProject(project);
                break;
            case 4:
                deleteProjectForm(project);
                break;
            case 5:
                controller.goToMainMenu();
                break;
        }
    }

    public void editProjectData(final Project project) {
        int sectionNumber = 0;

        String projectName = project.getName();
        Date projectCreation = project.getCreated();

        ConsoleHelper.printDelimiter();
        while (sectionNumber != 3) {
            ConsoleHelper.printMessage("Enter edit field number");
            ConsoleHelper.printMessage("1: Name");
            ConsoleHelper.printMessage("2: Date of creation");
            ConsoleHelper.printMessage("3: Finish editing");
            sectionNumber = ConsoleHelper.readInt(1, 3);
            switch (sectionNumber) {
                case 1: {
                    ConsoleHelper.printMessage("Enter new project name:");
                    projectName = ConsoleHelper.readString();
                    break;
                }
                case 2: {
                    ConsoleHelper.printMessage("Enter new date of creation in format dd/MM/yyyy (example 14/07/1993):");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        projectCreation = simpleDateFormat.parse(ConsoleHelper.readString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        controller.changeProjectData(project.getId(), projectName, projectCreation);
        ConsoleHelper.printMessage("Project has been updated");
        controller.goToEditProjectForm(project);
    }

    public void deleteProjectForm(final Project project) {
        controller.deleteProject(project);
        ConsoleHelper.printMessage("Project has been deleted");
        controller.goToMainMenu();
    }

    public void addProjectForm() {
        ConsoleHelper.printDelimiter();
        ConsoleHelper.printMessage("Enter project name:");
        final String name = ConsoleHelper.readString();
        controller.changeProjectData("0", name, new Date());
        ConsoleHelper.printMessage("Project has been added");
        controller.goToMainMenu();
    }


}
