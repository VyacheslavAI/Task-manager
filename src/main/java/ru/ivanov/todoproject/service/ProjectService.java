package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository = new ProjectRepository();

    private ServiceLocator serviceLocator;

    @Override
    public Project createOrUpdateProject(final Project project) {
        if (project == null) return null;
        return projectRepository.merge(project);
    }

    @Override
    public void addAllProject(final List<Project> projects) {
        if (projects == null || projects.isEmpty()) return;
        projectRepository.addAll(projects);
    }

    @Override
    public Project loadProjectById(final String id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> loadProjectByName(final String name) {
        if (name == null || name.isEmpty()) return Collections.emptyList();
        return projectRepository.findByName(name);
    }

    @Override
    public List<Project> loadAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> loadAllProjectByUser(final User user) {
        if (user == null) return null;
        final List<Project> projects = loadAllProject();
        final List<Project> result = new ArrayList<>();
        for (Project project : projects) {
            if (project.getUserId().equals(user.getId())) {
                result.add(project);
            }
        }
        return result;
    }

    @Override
    public Project deleteProject(final Project project) {
        if (project == null) return null;
        return projectRepository.delete(project);
    }

    @Override
    public void deleteAllProject() {
        projectRepository.deleteAll();
    }

    @Override
    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    @Override
    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
}
