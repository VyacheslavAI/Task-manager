package ru.ivanov.todoproject.api;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IProjectSOAPEndpoint {

    @WebMethod
    Project createProject(Project project);

    @WebMethod
    List<Project> readProject(String name);

    @WebMethod
    Project updateProject(Project project);

    @WebMethod
    Project deleteProject(Project project);

    @WebMethod
    List<Project> showProjects(Project project);
}
