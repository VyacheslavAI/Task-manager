package ru.ivanov.todoproject.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.validator.Validator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Transactional
@ApplicationScoped
public class ProjectService implements IProjectService {

    @Inject
    private IProjectRepository projectRepository;

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private Validator validator;

    @Override
    public Project createProject(final String userId, final Project project) throws ObjectIsNotValidException, InvalidArgumentException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
//        projectRepository.createProject(project);
        projectRepository.saveAndFlush(project);
        return project;
    }

    @Override
    public Project updateProject(final String userId, final Project project) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
        projectRepository.updateProject(project);
        return project;
    }

    @Override
    public boolean addAllProject(final List<Project> projects) {
        if (projects == null || projects.isEmpty()) return false;
        projects.removeAll(Collections.singleton(null));
        return true;
    }

    @Override
    public Project loadUserProjectById(final String userId, final String projectId) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, projectId)) throw new InvalidArgumentException();
        return projectRepository.findProjectById(userId, projectId);
    }

    @Override
    public Project loadUserProjectByName(final String userId, final String projectName) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, projectName)) throw new InvalidArgumentException();
        return projectRepository.findProjectByName(userId, projectName);
    }

    @Override
    public List<Project> loadAllUserProject(final String userId) throws InvalidArgumentException {
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
        return projectRepository.findAllProjectByUserId(userId);
    }

    @Override
    public List<Project> loadAllProject() {
        return projectRepository.findAllProject();
    }

    @Override
    public boolean deleteProject(final String userId, final String projectName) throws ObjectNotFoundException, InvalidArgumentException {
        if (!Validator.isArgumentsValid(userId, projectName)) throw new InvalidArgumentException();
        projectRepository.deleteProject(userId, projectName);
        return true;
    }

    @Override
    public boolean deleteAllProject() {
        projectRepository.deleteAllProject();
        return true;
    }
}