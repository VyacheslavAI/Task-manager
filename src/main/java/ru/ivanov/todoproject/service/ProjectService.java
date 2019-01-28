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

import java.util.ArrayList;
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
    public Project updateProject(final Project project) throws ObjectIsNotValidException, ObjectNotFoundException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        final Project persistentProject = projectRepository.findById(project.getId());
        if (persistentProject == null) throw new ObjectNotFoundException();
        if (!persistentProject.getUserId().equals(project.getUserId())) throw new ObjectNotFoundException();
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
        final Project persistentProject = projectRepository.findById(projectId);
        if (persistentProject == null) throw new ObjectNotFoundException();
        if (!persistentProject.getUserId().equals(userId)) throw new ObjectNotFoundException();
        return persistentProject;
    }

    @Override
    public List<Project> loadUserProjectByName(final String userId, final String name) throws InvalidArgumentException {
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        if (name == null || name.isEmpty()) return Collections.emptyList();
        final List<Project> projects = projectRepository.findByName(name);
        return filterProjectsByUserId(projects, userId);
    }

    @Override
    public List<Project> loadAllUserProject(final String userId) throws InvalidArgumentException {
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        List<Project> allProject = projectRepository.findAll();
        return filterProjectsByUserId(allProject, userId);
    }

    @Override
    public List<Project> loadAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public Project deleteProject(final Project project) throws ObjectIsNotValidException, ObjectNotFoundException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        final Project persistentProject = projectRepository.findById(project.getId());
        if (persistentProject == null) throw new ObjectNotFoundException();
        if (!persistentProject.getUserId().equals(project.getUserId())) throw new ObjectNotFoundException();
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
    public void setServiceLocator(final ServiceLocator serviceLocator) {
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