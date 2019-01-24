package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.RequestNotAuthenticatedException;

import javax.jws.WebService;
import java.util.List;

import static ru.ivanov.todoproject.util.CollectionUtil.filterProjectsByUserId;
import static ru.ivanov.todoproject.util.HashUtil.isSessionVerified;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.IProjectSOAPEndpoint")
public class ProjectSOAPEndpoint implements IProjectSOAPEndpoint {

    private ServiceLocator serviceLocator;

    @Override
    public Project createProject(final Session session, final Project project) throws RequestNotAuthenticatedException, ObjectIsNotValidException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        project.setUserId(session.getUserId());
        return serviceLocator.getProjectService().createOrUpdateProject(project);
    }

    @Override
    public List<Project> readProject(final Session session, final String name) throws RequestNotAuthenticatedException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        final List<Project> projects = serviceLocator.getProjectService().loadProjectByName(name);
        return filterProjectsByUserId(projects, session.getUserId());
    }

    @Override
    public Project updateProject(final Session session, final Project project) throws RequestNotAuthenticatedException, ObjectIsNotValidException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        return serviceLocator.getProjectService().createOrUpdateProject(project);
    }

    @Override
    public Project deleteProject(final Session session, final Project project) throws RequestNotAuthenticatedException, ObjectIsNotValidException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        return serviceLocator.getProjectService().deleteProject(project);
    }

    @Override
    public List<Project> showProjects(final Session session) throws RequestNotAuthenticatedException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        final List<Project> allProjects = serviceLocator.getProjectService().loadAllProject();
        return filterProjectsByUserId(allProjects, session.getUserId());
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