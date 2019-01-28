package ru.ivanov.todoproject.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.AuthenticationException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebService
public interface IProjectSOAPEndpoint {

    @WebMethod
    Project createProject(final Session session, final Project project) throws AuthenticationException, ObjectIsNotValidException, JsonProcessingException, NoSuchAlgorithmException, InvalidArgumentException;

    @WebMethod
    List<Project> readProject(final Session session, final String name) throws AuthenticationException, InvalidArgumentException;

    @WebMethod
    Project updateProject(final Session session, final Project project) throws AuthenticationException, ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException;

    @WebMethod
    Project deleteProject(final Session session, final Project project) throws ObjectIsNotValidException, AuthenticationException, ObjectNotFoundException;

    @WebMethod
    List<Project> showProjects(final Session session) throws AuthenticationException, InvalidArgumentException;
}
