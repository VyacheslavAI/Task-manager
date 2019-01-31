package ru.ivanov.todoproject.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.AuthenticationException;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.security.SecurityServerManager;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebService
public interface IProjectSOAPEndpoint {

    @WebMethod
    Project createProject(final Session session, final Project project) throws AuthenticationException, ObjectIsNotValidException, JsonProcessingException, NoSuchAlgorithmException, InvalidArgumentException;

    @WebMethod
    Project readProject(final Session session, final String name) throws AuthenticationException, InvalidArgumentException, ObjectNotFoundException;

    @WebMethod
    Project updateProject(final Session session, final Project project) throws AuthenticationException, ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException;

    @WebMethod
    Project deleteProject(Session session, String projectName) throws AuthenticationException, ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException;

    @WebMethod
    List<Project> showProjects(final Session session) throws AuthenticationException, InvalidArgumentException;

    @WebMethod(exclude = true)
    void setSecurityServerManager(SecurityServerManager securityManager);

    @WebMethod(exclude = true)
    void setServiceLocator(ServiceLocator serviceLocator);
}
