
package ru.ivanov.todoproject.service;

import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.repository.IProjectRepository;
import ru.ivanov.todoproject.util.Validator;

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
    public Project updateProject(final Project project) throws ObjectIsNotValidException, ObjectNotFoundException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        if (!projectRepository.existsById(project.getId())) throw new ObjectNotFoundException();
        projectRepository.save(project);
        return project;
    }

    @Override
    public boolean addAllProject(final List<Project> projects) {
        if (projects == null || projects.isEmpty()) return false;
        projects.removeAll(Collections.singleton(null));
        projectRepository.saveAll(projects);
        return true;
    }

    @Override
    public Project findProjectById(final String userId, final String projectId) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, projectId)) throw new InvalidArgumentException();
        if (!projectRepository.existsById(projectId)) throw new ObjectNotFoundException();
        return projectRepository.findProjectByUserIdAndId(userId, projectId);
    }

    @Override
    public Project findProjectByName(final String userId, final String projectName) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, projectName)) throw new InvalidArgumentException();
        final Project project = projectRepository.findProjectByUserIdAndName(userId, projectName);
        if (project == null) throw new ObjectNotFoundException();
        return project;
    }

    @Override
    public List<Project> findAllUserProject(final String userId) throws InvalidArgumentException {
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
        return projectRepository.findAllByUserId(userId);
    }

    @Override
    public List<Project> findAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public boolean deleteProject(String projectId) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(projectId)) throw new InvalidArgumentException();
        projectRepository.deleteById(projectId);
        return true;
    }

    @Override
    public boolean deleteAllProject() {
        projectRepository.deleteAllInBatch();
        return true;
    }
}