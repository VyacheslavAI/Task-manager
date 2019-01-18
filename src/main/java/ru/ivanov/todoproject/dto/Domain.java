package ru.ivanov.todoproject.dto;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

import java.io.Serializable;
import java.util.List;

public class Domain implements Serializable {

    private List<Project> projects;

    private List<Task> tasks;

    private List<User> users;

    public List<Project> getProjects() {
        return projects;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    private void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    private void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Domain() {
    }

    public static Domain createDomain(final ServiceLocator serviceLocator) {
        final Domain domain = new Domain();
        domain.setProjects(serviceLocator.getProjectService().loadAllProject());
        domain.setTasks(serviceLocator.getTaskService().loadAllTask());
        domain.setUsers(serviceLocator.getUserService().loadAllUser());
        return domain;
    }

    public void loadFromDomain(final ServiceLocator serviceLocator) {
        serviceLocator.getUserService().addAllUser(getUsers());
        serviceLocator.getProjectService().addAllProject(getProjects());
        serviceLocator.getTaskService().addAllTask(getTasks());
    }
}
