package ru.ivanov.todoproject.controller;

import ru.ivanov.todoproject.dao.ProjectRepository;
import ru.ivanov.todoproject.dao.TaskRepository;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.util.ConsoleHelper;
import ru.ivanov.todoproject.util.SerializationUtil;
import ru.ivanov.todoproject.view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {

    private ProjectRepository projectRepo = new ProjectRepository();

    private TaskRepository taskRepo = new TaskRepository();

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

    public ProjectRepository getProjectRepo() {
        return projectRepo;
    }

    public void setProjectRepo(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }

    public TaskRepository getTaskRepo() {
        return taskRepo;
    }

    public void setTaskRepo(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public ProjectView getProjectView() {
        return projectView;
    }

    public void setProjectView(ProjectView projectView) {
        this.projectView = projectView;
    }

    public ProjectEditView getProjectEditView() {
        return projectEditView;
    }

    public void setProjectEditView(ProjectEditView projectEditView) {
        this.projectEditView = projectEditView;
    }

    public TaskView getTaskView() {
        return taskView;
    }

    public void setTaskView(TaskView taskView) {
        this.taskView = taskView;
    }

    public TaskEditView getTaskEditView() {
        return taskEditView;
    }

    public void setTaskEditView(TaskEditView taskEditView) {
        this.taskEditView = taskEditView;
    }

    public List<Project> loadProjectsByName(String name) {
        return projectRepo.getProjectsByName(name);
    }

    public Project changeProjectData(String id, String name, Date created) {
        Project project = new Project(id, name, created);
        return projectRepo.createOrUpdateProject(project);
    }

    public List<Project> loadAllProjects() {
        return projectRepo.getAllProjects();
    }

    public Project deleteProject(Project project) {
        return projectRepo.deleteProject(project);
    }

    public List<Task> loadTaskByName(String name) {
        return taskRepo.getTasksByName(name);
    }

    public Task changeTaskData(String id, String projectId, String name, Date created) {
        Task task = new Task(id, projectId, name, created);
        return taskRepo.createOrUpdateTask(task);
    }

    public List<Task> loadAllTasks() {
        return taskRepo.getAllTasks();
    }

    public Task deleteTask(Task task) {
        return taskRepo.deleteTask(task);
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

    public List<Task> loadTasksByProject(final Project project) {
        final List<Task> tasks = taskRepo.getAllTasks();
        for (final Task task : tasks) {
            if (project.getId().equals(task.getProjectId())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public void showTasksForProject(final Project project) {
        taskView.showAndSelectTask(loadTasksByProject(project));
    }

    public void addProject() {
        projectEditView.addProjectForm();
    }

    public void addTaskForProject(final Project project) {
        taskEditView.addTaskForm(project);
    }

    public void saveState() {
        try {
            final List<List> data = new ArrayList<>();
            data.add(loadAllProjects());
            data.add(loadAllTasks());
            SerializationUtil.serialize(new SerializationUtil.Domain(data));
        } catch (IOException e) {
            ConsoleHelper.printMessage("An error occurred during serialization");
        }
    }

    public void loadState() {
        try {
            taskRepo.deleteAllTasks();
            projectRepo.deleteAllProjects();
            final List<List> data = SerializationUtil.deserialize();
            final List<Project> projects = (List<Project>) data.get(0);
            final List<Task> tasks = (List<Task>) data.get(1);
            projectRepo.addAllProjects(projects);
            taskRepo.addAllTasks(tasks);
        } catch (Exception e) {
            ConsoleHelper.printMessage("An error occurred during deserialization");
        }
    }
}
