package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ClientInterface {

    @WebMethod
    Project createProject();

    @WebMethod
    Project updateProject();

    @WebMethod
    Project readProject();

    @WebMethod
    Project deleteProject();

    @WebMethod
    Task createTask();

    @WebMethod
    Task updateTask();

    @WebMethod
    Task readTask();

    @WebMethod
    Task deleteTask();

    @WebMethod
    User createUser();

    @WebMethod
    User updateUser();

    @WebMethod
    User readUser();

    @WebMethod
    User deleteUser();
}
