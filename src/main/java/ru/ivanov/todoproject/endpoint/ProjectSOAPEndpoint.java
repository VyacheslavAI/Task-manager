package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.IProjectSOAPEndpoint")
public class ProjectSOAPEndpoint implements IProjectSOAPEndpoint {

    private ServiceLocator serviceLocator;

    @Override
    public Project createProject(final Session session, final Project project) {
        final boolean isUserAuthorized = serviceLocator.getUserService().isUserAuthorized(session);
        if (!isUserAuthorized) {
            return null;
        }
        project.setUserId(session.getUserId());
        return serviceLocator.getProjectService().createOrUpdateProject(project);
    }

    @Override
    public List<Project> readProject(final Session session, final String name) {
        final boolean isUserAuthorized = serviceLocator.getUserService().isUserAuthorized(session);
        if (!isUserAuthorized) {
            return null;
        }
        final List<Project> projects = serviceLocator.getProjectService().loadProjectByName(name);
        serviceLocator.getUserService().filterProjectsForUser(session, projects);
        return projects;
    }

    @Override
    public Project updateProject(final Session session, final Project project) {
        final boolean isUserAuthorized = serviceLocator.getUserService().isUserAuthorized(session);
        if (!isUserAuthorized) {
            return null;
        }
        return serviceLocator.getProjectService().createOrUpdateProject(project);
    }

    @Override
    public Project deleteProject(final Session session, final Project project) {
        final boolean isUserAuthorized = serviceLocator.getUserService().isUserAuthorized(session);
        if (!isUserAuthorized) {
            return null;
        }
        return serviceLocator.getProjectService().deleteProject(project);
    }

    @Override
    public List<Project> showProjects(final Session session) {
        final boolean isUserAuthorized = serviceLocator.getUserService().isUserAuthorized(session);
        if (!isUserAuthorized) {
            return null;
        }
        return serviceLocator.getProjectService().loadAllProject();
    }
}