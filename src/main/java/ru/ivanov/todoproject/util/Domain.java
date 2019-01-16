package ru.ivanov.todoproject.util;

import ru.ivanov.todoproject.controller.Controller;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;

import java.io.Serializable;
import java.util.List;

public class Domain implements Serializable {

    private List<Project> projects;

    private List<Task> tasks;

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

    public Domain() {
    }

    public static Domain createDomain(final Controller controller) {
        final Domain domain = new Domain();
        domain.setProjects(controller.getProjectService().loadAllProject());
        domain.setTasks(controller.getTaskService().loadAllTask());
        return domain;
    }
}
