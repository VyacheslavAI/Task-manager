package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.dao.ProjectRepository;
import ru.ivanov.todoproject.entity.Project;

import java.util.Collections;
import java.util.List;

public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository = new ProjectRepository();

    public Project createOrUpdateProject(final Project project) {
        if (project == null) return null;
        return projectRepository.merge(project);
    }

    public void addAllProject(final List<Project> projects) {
        if (projects == null || projects.isEmpty()) return;
        projectRepository.addAll(projects);
    }

    public Project loadById(final String id) {
        return projectRepository.findById(id);
    }

    public List<Project> loadProjectByName(final String name) {
        if (name == null || name.isEmpty()) return Collections.emptyList();
        return projectRepository.findByName(name);
    }

    public List<Project> loadAllProject() {
        return projectRepository.findAll();
    }

    public Project deleteProject(final Project project) {
        if (project == null) return null;
        return projectRepository.delete(project);
    }

    public void deleteAllProject() {
        projectRepository.deleteAll();
    }
}
