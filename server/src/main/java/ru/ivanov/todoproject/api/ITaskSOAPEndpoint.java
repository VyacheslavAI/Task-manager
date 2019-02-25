package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ITaskSOAPEndpoint {

    @WebMethod
    Task createTask(
            @WebParam(name = "userId") String userId,
            @WebParam(name = "projectId") String projectId,
            @WebParam(name = "task") Task task) throws ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException;

    @WebMethod
    Task updateTask(
            @WebParam(name = "task") Task task) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException;

    @WebMethod
    Task findTaskById(
            @WebParam(name = "taskId")  String taskId) throws InvalidArgumentException, ObjectNotFoundException;

    @WebMethod
    boolean deleteTask(
            @WebParam(name = "taskId") String taskId) throws InvalidArgumentException, ObjectNotFoundException;

}
