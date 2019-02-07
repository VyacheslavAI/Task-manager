
package ru.ivanov.todoproject.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.repository.IProjectRepository;
import ru.ivanov.todoproject.validator.Validator;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Transactional
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
        project.setUserId(userId);
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(final Project project) throws ObjectIsNotValidException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        projectRepository.save(project);
        return project;
    }

    @Override
    public boolean addAllProject(final List<Project> projects) {
        if (projects == null || projects.isEmpty()) return false;
        projects.removeAll(Collections.singleton(null));
        for (final Project project : projects) {
            projectRepository.save(project);
        }
        return true;
    }

    @Override
    public Project findProjectById(final String userId, final String projectId) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, projectId)) throw new InvalidArgumentException();
        final Project project = projectRepository.findBy(projectId);
        if (project == null) throw new ObjectNotFoundException();
        return project;
    }

    @Override
    public Project findProjectByName(final String userId, final String projectName) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, projectName)) throw new InvalidArgumentException();
        final Project project = projectRepository.findProjectByName(userId, projectName);
        if (project == null) throw new ObjectNotFoundException();
        return project;
    }

    @Override
    public List<Project> findAllUserProject(final String userId) throws InvalidArgumentException {
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
        return projectRepository.findAllProjectByUserId(userId);
    }

    @Override
    public List<Project> findAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public boolean deleteProject(final String userId, String projectName) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, projectName)) throw new InvalidArgumentException();
        final Project project = projectRepository.findProjectByName(userId, projectName);
        if (project == null) throw new ObjectNotFoundException();
        projectRepository.deleteProject(userId, projectName);
        return true;
    }

    @Override
    public boolean deleteAllProject() {
        projectRepository.deleteAllProject();
        return true;
    }
}