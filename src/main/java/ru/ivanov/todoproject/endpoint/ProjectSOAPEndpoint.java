package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.AuthenticationException;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.security.SecurityServerManager;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.IProjectSOAPEndpoint")
public class ProjectSOAPEndpoint implements IProjectSOAPEndpoint {

    private ServiceLocator serviceLocator;

    private SecurityServerManager securityManager;

    @Override
    public Project createProject(final Session session, final Project project) throws AuthenticationException, ObjectIsNotValidException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getProjectService().createProject(session.getUserId(), project);
    }

    @Override
    public List<Project> readProject(final Session session, final String name) throws AuthenticationException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getProjectService().loadUserProjectByName(session.getUserId(), name);
    }

    @Override
    public Project updateProject(final Session session, final Project project) throws AuthenticationException, ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getProjectService().updateProject(project);
    }

    @Override
    public Project deleteProject(final Session session, final Project project) throws AuthenticationException, ObjectIsNotValidException, ObjectNotFoundException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getProjectService().deleteProject(project);
    }

    @Override
    public List<Project> showProjects(final Session session) throws AuthenticationException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getProjectService().loadAllUserProject(session.getUserId());
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void setSecurityManager(SecurityServerManager securityManager) {
        this.securityManager = securityManager;
    }
}