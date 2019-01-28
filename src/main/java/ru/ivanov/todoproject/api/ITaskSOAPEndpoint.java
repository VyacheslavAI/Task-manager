package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.AuthenticationException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ITaskSOAPEndpoint {

    @WebMethod
    Task createTask(final Session session, final Task task) throws AuthenticationException, ObjectIsNotValidException;

    @WebMethod
    List<Task> readTask(final Session session, final String name) throws AuthenticationException;

    @WebMethod
    Task updateTask(final Session session, final Task task) throws ObjectIsNotValidException, AuthenticationException;

    @WebMethod
    Task deleteTask(final Session session, final Task task) throws ObjectIsNotValidException, AuthenticationException;

    @WebMethod
    List<Task> showTasks(final Session session) throws AuthenticationException;

    @WebMethod
    List<Task> getTasksByProject(final Session session, final Project project) throws ObjectIsNotValidException, AuthenticationException;
}
