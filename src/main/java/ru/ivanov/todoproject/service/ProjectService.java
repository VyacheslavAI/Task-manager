package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.repository.ProjectRepository;
import ru.ivanov.todoproject.validator.Validator;

import java.util.Collections;
import java.util.List;

public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository = new ProjectRepository();

    private ServiceLocator serviceLocator;

    private Validator validator;

    @Override
    public Project createProject(final String userId, final Project project) throws ObjectIsNotValidException, InvalidArgumentException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        project.setUserId(userId);
        return projectRepository.merge(project);
    }

    @Override
    public Project updateProject(final String userId, final Project project) throws ObjectIsNotValidException, ObjectNotFoundException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        final Project persistentProject = projectRepository.findProjectById(userId, project.getId());
        if (persistentProject == null) throw new ObjectNotFoundException();
        return projectRepository.merge(project);
    }

    @Override
    public boolean addAllProject(final List<Project> projects) {
        if (projects == null || projects.isEmpty()) return false;
        projects.removeAll(Collections.singleton(null));
        return projectRepository.addAll(projects);
    }

    @Override
    public Project loadUserProjectById(final String userId, final String projectId) throws InvalidArgumentException, ObjectNotFoundException {
        if (projectId == null || projectId.isEmpty()) throw new InvalidArgumentException();
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        final Project persistentProject = projectRepository.findProjectById(userId, projectId);
        if (persistentProject == null) throw new ObjectNotFoundException();
        return persistentProject;
    }

    @Override
    public Project loadUserProjectByName(final String userId, final String projectName) throws InvalidArgumentException, ObjectNotFoundException {
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        if (projectName == null || projectName.isEmpty()) throw new InvalidArgumentException();
        final Project project = projectRepository.findProjectByName(userId, projectName);
        if (project == null) throw new ObjectNotFoundException();
        return project;
    }

    @Override
    public List<Project> loadAllUserProject(final String userId) throws InvalidArgumentException {
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        return projectRepository.findAllProject(userId);
    }

    @Override
    public List<Project> loadAllProject() {
        return projectRepository.findAllProject();
    }

    @Override
    public Project deleteProject(final String userId, final String projectName) throws ObjectNotFoundException, InvalidArgumentException {
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        if (projectName == null || projectName.isEmpty()) throw new InvalidArgumentException();
        final Project project = projectRepository.findProjectByName(userId, projectName);
        if (project == null) throw new ObjectNotFoundException();
        return projectRepository.delete(project);
    }

    @Override
    public boolean deleteAllProject() {
        return projectRepository.deleteAll();
    }

    @Override
    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}