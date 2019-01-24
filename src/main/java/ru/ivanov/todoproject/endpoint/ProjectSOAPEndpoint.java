package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.IPredicate;
import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.RequestNotAuthenticatedException;

import javax.jws.WebService;
import java.util.List;

import static ru.ivanov.todoproject.util.CollectionUtil.filter;
import static ru.ivanov.todoproject.util.HashUtil.isSessionVerified;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.IProjectSOAPEndpoint")
public class ProjectSOAPEndpoint implements IProjectSOAPEndpoint {

    private ServiceLocator serviceLocator;

    @Override
    public Project createProject(final Session session, final Project project) throws RequestNotAuthenticatedException, ObjectIsNotValidException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        project.setId(session.getUserId());
        return serviceLocator.getProjectService().createOrUpdateProject(project);
    }

    @Override
    public List<Project> readProject(final Session session, final String name) throws RequestNotAuthenticatedException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        final List<Project> loadProjects = serviceLocator.getProjectService().loadProjectByName(name);
        final IPredicate<Project> belongsToUser = new IPredicate<Project>() {
            public boolean apply(final Project project) {
                return project.getName().equals(name);
            }
        };
        final List<Project> result = filter(loadProjects, belongsToUser);
        return result;
    }

    @Override
    public Project updateProject(final Session session, final Project project) {
        if (session == null || project == null) return null;
        final User user = serviceLocator.getUserService().getUserBySession(session);
        if (user == null || !project.getUserId().equals(user.getId())) return null;
        return serviceLocator.getProjectService().createOrUpdateProject(project);
    }

    @Override
    public Project deleteProject(final Session session, final Project project) {
        if (session == null || project == null) return null;
        final User user = serviceLocator.getUserService().getUserBySession(session);
        if (user == null || !project.getUserId().equals(user.getId())) return null;
        return serviceLocator.getProjectService().deleteProject(project);
    }

    @Override
    public List<Project> showProjects(final Session session) {
        if (session == null) return null;
        final User user = serviceLocator.getUserService().getUserBySession(session);
        if (user == null) return null;
        return serviceLocator.getProjectService().loadAllProject();
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
}