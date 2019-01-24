package ru.ivanov.todoproject.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.RequestNotAuthenticatedException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebService
public interface IProjectSOAPEndpoint {

    @WebMethod
    Project createProject(final Session session, final Project project) throws RequestNotAuthenticatedException, ObjectIsNotValidException, JsonProcessingException, NoSuchAlgorithmException;

    @WebMethod
    List<Project> readProject(final Session session, final String name);

    @WebMethod
    Project updateProject(final Session session, final Project project);

    @WebMethod
    Project deleteProject(final Session session, final Project project);

    @WebMethod
    List<Project> showProjects(final Session session);
}
