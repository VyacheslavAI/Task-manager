package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.repository.ProjectRepository;
import ru.ivanov.todoproject.validator.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository = new ProjectRepository();

    private ServiceLocator serviceLocator;

    private Validator validator;

    @Override
    public Project createOrUpdateProject(final Project project) throws ObjectIsNotValidException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException(project);
        return projectRepository.merge(project);
    }

    @Override
    public boolean addAllProject(final List<Project> projects) {
        if (projects == null || projects.isEmpty()) return false;
        projects.removeAll(Collections.singleton(null));
        return projectRepository.addAll(projects);
    }

    @Override
    public Project loadProjectById(final String id) throws InvalidArgumentException {
        if (id == null || id.isEmpty()) throw new InvalidArgumentException();
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
        if (!validator.isUserValid(user)) throw new ObjectIsNotValidException(user);
        final List<Project> projects = loadAllProject();
        return filterProjectsByUserId(projects, user.getId());
    }

    @Override
    public Project deleteProject(final Project project) throws ObjectIsNotValidException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException(project);
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

    private List<Project> filterProjectsByUserId(final List<Project> projects, final String userId) {
        if (projects == null || projects.isEmpty()) return Collections.emptyList();
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        final List<Project> result = new ArrayList<>();
        for (final Project project : projects) {
            if (project.getUserId().equals(userId)) {
                result.add(project);
            }
        }
        return result;
    }
}
