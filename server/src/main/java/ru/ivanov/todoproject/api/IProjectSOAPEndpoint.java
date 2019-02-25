package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IProjectSOAPEndpoint {

    @WebMethod
    Project createProject(
            @WebParam(name = "userId") String userId,
            @WebParam(name = "projectName") String projectName) throws ObjectIsNotValidException, InvalidArgumentException;

    @WebMethod
    Project updateProject(
            @WebParam(name = "project") Project project) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException;

    @WebMethod
    Project findProjectById(
            @WebParam(name = "userId") String userId,
            @WebParam(name = "projectId") String projectId) throws ObjectNotFoundException, InvalidArgumentException;

    @WebMethod
    boolean deleteProject(
            @WebParam(name = "projectId") String projectId) throws InvalidArgumentException, ObjectNotFoundException;
}
