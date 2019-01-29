package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.AuthenticationException;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.security.SecurityServerManager;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ITaskSOAPEndpoint {

    @WebMethod
    Task createTask(final Session session, final Task task) throws AuthenticationException, ObjectIsNotValidException, InvalidArgumentException;

    @WebMethod
    Task readTask(Session session, Project project, String taskName) throws AuthenticationException, ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException;

    @WebMethod
    Task updateTask(final Session session, final Task task) throws ObjectIsNotValidException, AuthenticationException, ObjectNotFoundException, InvalidArgumentException;

    @WebMethod
    Task deleteTask(Session session, String projectId, String taskName) throws AuthenticationException, ObjectNotFoundException, InvalidArgumentException;

    @WebMethod
    List<Task> showTasks(final Session session) throws AuthenticationException, InvalidArgumentException;

    @WebMethod
    List<Task> getAllTaskByProject(final Session session, final Project project) throws ObjectIsNotValidException, AuthenticationException, InvalidArgumentException;

    void setSecurityServerManager(SecurityServerManager securityManager);

//    void setServiceLocator(ServiceLocator serviceLocator);
}
