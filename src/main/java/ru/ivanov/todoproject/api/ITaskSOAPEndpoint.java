package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ITaskSOAPEndpoint {

    @WebMethod
    Task createTask(final Task task);

    @WebMethod
    List<Task> readTask(final String name);

    @WebMethod
    Task updateTask(final Task task);

    @WebMethod
    Task deleteTask(final Task task);

    @WebMethod
    List<Task> showTasks();

    @WebMethod
    List<Task> getTasksByProject(final Project project);
}
