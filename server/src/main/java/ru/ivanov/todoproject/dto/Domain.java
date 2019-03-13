package ru.ivanov.todoproject.dto;

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

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void setTasks(List<Task> tasks) {
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
}