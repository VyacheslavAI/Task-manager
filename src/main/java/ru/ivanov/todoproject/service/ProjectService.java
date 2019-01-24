package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.ivanov.todoproject.util.ValidationUtil.isProjectValid;
import static ru.ivanov.todoproject.util.ValidationUtil.isUserValid;

public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository = new ProjectRepository();

    private ServiceLocator serviceLocator;

    @Override
    public Project createOrUpdateProject(final Project project) throws ObjectIsNotValidException {
        if (!isProjectValid(project)) throw new ObjectIsNotValidException(project);
        return projectRepository.merge(project);
    }

    @Override
    public boolean addAllProject(final List<Project> projects) {
        if (projects == null || projects.isEmpty()) return false;
        projects.removeAll(Collections.singleton(null));
        return projectRepository.addAll(projects);
    }

    @Override
    public Project loadProjectById(final String id) throws IllegalArgumentException {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException();
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
    public List<Project> loadAllProjectByUser(final User user) throws ObjectIsNotValidException {
        if (!isUserValid(user)) throw new ObjectIsNotValidException(user);
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
    public Project deleteProject(final Project project) throws ObjectIsNotValidException {
        if (!isProjectValid(project)) throw new ObjectIsNotValidException(project);
        return projectRepository.delete(project);
    }

    @Override
    public boolean deleteAllProject() {
        return projectRepository.deleteAll();
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
