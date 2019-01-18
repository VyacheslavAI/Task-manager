package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ITaskSOAPEndpoint {

    @WebMethod
    Task createTask(Task task);

    @WebMethod
    List<Task> readTask(String name);

    @WebMethod
    Task updateTask(Task task);

    @WebMethod
    Task deleteTask(Task task);

    @WebMethod
    List<Task> showTasks();
}
