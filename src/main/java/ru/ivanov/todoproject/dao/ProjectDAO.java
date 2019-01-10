package ru.ivanov.todoproject.dao;

import ru.ivanov.todoproject.api.IProjectDAO;
import ru.ivanov.todoproject.entity.Project;

import java.util.List;

public class ProjectDAO implements IProjectDAO {

    private AllProjects allProjects = new AllProjects();

    @Override
    public Project createOrUpdateProject(Project project) {
        return allProjects.createOrUpdateProject(project);
    }

    @Override
    public Project deleteProject(Project project) {
        return allProjects.deleteProject(project);
    }

    @Override
    public List<Project> getProjectsByName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();

        return allProjects.withName(name);
    }

    @Override
    public List<Project> getAllProjects() {
        return allProjects.getAllProjects();
    }

    @Override
    public void deleteAllProjects() {
        allProjects.clearAll();
    }

    @Override
    public void addAllProjects(List<Project> projects) {
        allProjects.addAll(projects);
    }
}
