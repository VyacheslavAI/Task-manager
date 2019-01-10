package ru.ivanov.todoproject.controller;

import ru.ivanov.todoproject.api.IProjectDAO;
import ru.ivanov.todoproject.api.ITaskDAO;
import ru.ivanov.todoproject.dao.ProjectDAO;
import ru.ivanov.todoproject.dao.TaskDAO;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.util.ConsoleHelper;
import ru.ivanov.todoproject.view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {

    private IProjectDAO projectDAO = new ProjectDAO();

    private ITaskDAO taskDAO = new TaskDAO();

    private MainView mainView = new MainView();

    private ProjectView projectView = new ProjectView();

    private ProjectEditView projectEditView = new ProjectEditView();

    private TaskView taskView = new TaskView();

    private TaskEditView taskEditView = new TaskEditView();

    //TESTINIT
    public Controller() {
        projectView.setController(this);
        taskView.setController(this);
        projectEditView.setController(this);
        taskEditView.setController(this);
        mainView.setController(this);
    }

    public List<Project> loadProjectsByName(String name) {
        return projectDAO.getProjectsByName(name);
    }

    public Project changeProjectData(String id, String name, Date created) {
        Project project = new Project(id, name, created);
        return projectDAO.createOrUpdateProject(project);
    }

    public List<Project> loadAllProjects() {
        return projectDAO.getAllProjects();
    }

    public Project deleteProject(Project project) {
        return projectDAO.deleteProject(project);
    }

    public List<Task> loadTaskByName(String name) {
        return taskDAO.getTasksByName(name);
    }

    public Task changeTaskData(String id, String projectId, String name, Date created) {
        Task task = new Task(id, projectId, name, created);
        return taskDAO.createOrUpdateTask(task);
    }

    public List<Task> loadAllTasks() {
        return taskDAO.getAllTasks();
    }

    public Task deleteTask(Task task) {
        return taskDAO.deleteTask(task);
    }

    public void goToMainMenu() {
        mainView.showMainMenu();
    }

    public void showProjects() {
        projectView.showAndSelectProjects(loadAllProjects());
    }

    public void showTasks() {
        taskView.showAndSelectTask(loadAllTasks());
    }

    public void goToEditProjectForm(Project project) {
        projectEditView.editProjectForm(project);
    }

    public void goToEditTaskForm(Task task) {
        taskEditView.editTaskForm(task);
    }

    public List<Task> loadTasksByProject(Project project) {
        List<Task> tasks = taskDAO.getAllTasks();
        for (Task task : tasks) {
            if (project.getId().equals(task.getProjectId())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public void showTasksForProject(Project project) {
        taskView.showAndSelectTask(loadTasksByProject(project));
    }

    public void addProject() {
        projectEditView.addProjectForm();
    }

    public void addTaskForProject(Project project) {
        taskEditView.addTaskForm(project);
    }

    public void saveState() {
        try {
            List<List> data = new ArrayList<>();
            data.add(loadAllProjects());
            data.add(loadAllTasks());
            SerializationUtils.serialize(new SerializationUtils.Domen(data));
        } catch (IOException e) {
            ConsoleHelper.printMessage("An error occurred during serialization");
        }
    }

    public void loadState() {
        try {
            taskDAO.deleteAllTasks();
            projectDAO.deleteAllProjects();
            List<List> data = null;
            data = SerializationUtils.deserialize();
            List<Project> projects = (List<Project>) data.get(0);
            List<Task> tasks = (List<Task>) data.get(1);
            projectDAO.addAllProjects(projects);
            taskDAO.addAllTasks(tasks);
        } catch (Exception e) {
            ConsoleHelper.printMessage("An error occurred during deserialization");
        }
    }
}
