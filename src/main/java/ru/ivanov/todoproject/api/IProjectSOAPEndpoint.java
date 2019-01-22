package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IProjectSOAPEndpoint {

    @WebMethod
    Project createProject(final Session session, final Project project);

    @WebMethod
    List<Project> readProject(final Session session, final String name);

    @WebMethod
    Project updateProject(final Session session, final Project project);

    @WebMethod
    Project deleteProject(final Session session, final Project project);

    @WebMethod
    List<Project> showProjects(final Session session);
}
