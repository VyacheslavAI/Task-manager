package ru.ivanov.todoproject.dto;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

import java.io.Serializable;
import java.util.List;

public class Domain implements Serializable {

    private List<Project> projects;

    private List<Task> tasks;

    private List<User> users;

    private List<Session> sessions;

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

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Domain() {
    }

    public static Domain createDomain(final ServiceLocator serviceLocator) {
        final Domain domain = new Domain();
        domain.setProjects(serviceLocator.getProjectService().loadAllProject());
        domain.setTasks(serviceLocator.getTaskService().loadAllTask());
        domain.setUsers(serviceLocator.getUserService().loadAllUser());
        domain.setSessions(serviceLocator.getSessionService().loadAllSession());
        return domain;
    }

    public void loadFromDomain(final ServiceLocator serviceLocator) {
        serviceLocator.getUserService().addAllUser(getUsers());
        serviceLocator.getProjectService().addAllProject(getProjects());
        serviceLocator.getTaskService().addAllTask(getTasks());
        serviceLocator.getSessionService().addAllSession(getSessions());
    }
}
