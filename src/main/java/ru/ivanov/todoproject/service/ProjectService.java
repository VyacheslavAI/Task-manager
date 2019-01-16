package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.dao.ProjectRepository;
import ru.ivanov.todoproject.entity.Project;

import java.util.Collections;
import java.util.List;

public class ProjectService implements IProjectService {

    private IProjectRepository projectRepo = new ProjectRepository();

    public Project createOrUpdateProject(final Project project) {
        if (project == null)
            return null;

        return projectRepo.merge(project);
    }

    public void addAllProject(final List<Project> projects) {
        if (projects == null || projects.isEmpty()) return;

        projectRepo.addAllEntity(projects);
    }

    public List<Project> loadProjectByName(final String name) {
        if (name == null || name.isEmpty())
            return Collections.emptyList();

        return projectRepo.getEntitiesByName(name);
    }

    public List<Project> loadAllProject() {
        return projectRepo.getAllEntity();
    }

    public Project deleteProject(final Project project) {
        if (project == null)
            return null;

        return projectRepo.deleteEntity(project);
    }

    public void deleteAllProject() {
        projectRepo.deleteAllEntity();
    }
}
