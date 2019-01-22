package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ITaskSOAPEndpoint {

    @WebMethod
    Task createTask(final Session session, final Task task);

    @WebMethod
    List<Task> readTask(final Session session, final String name);

    @WebMethod
    Task updateTask(final Session session, final Task task);

    @WebMethod
    Task deleteTask(final Session session, final Task task);

    @WebMethod
    List<Task> showTasks(final Session session);

    @WebMethod
    List<Task> getTasksByProject(final Session session, final Project project);
}
