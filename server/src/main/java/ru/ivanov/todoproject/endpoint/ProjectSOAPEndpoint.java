package ru.ivanov.todoproject.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.IProjectSOAPEndpoint")
public class ProjectSOAPEndpoint implements IProjectSOAPEndpoint {

    @Autowired
    private ServiceLocator serviceLocator;

    @Override
    public Project createProject(String userId, String projectName) throws ObjectIsNotValidException, InvalidArgumentException {
        final Project project = new Project();
        project.setName(projectName);
        return serviceLocator.getProjectService().createProject(userId, project);
    }

    @Override
    public Project updateProject(Project project) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        return serviceLocator.getProjectService().updateProject(project);
    }

    @Override
    public Project findProjectById(String userId, String projectId) throws ObjectNotFoundException, InvalidArgumentException {
        return serviceLocator.getProjectService().findProjectById(userId, projectId);
    }

    @Override
    public boolean deleteProject(String projectId) throws InvalidArgumentException, ObjectNotFoundException {
        return serviceLocator.getProjectService().deleteProject(projectId);
    }
}
