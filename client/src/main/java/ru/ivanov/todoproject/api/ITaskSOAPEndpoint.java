package ru.ivanov.todoproject.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-01-22T17:50:52.548+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://api.todoproject.ivanov.ru/", name = "ITaskSOAPEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface ITaskSOAPEndpoint {

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/ITaskSOAPEndpoint/showTasksRequest", output = "http://api.todoproject.ivanov.ru/ITaskSOAPEndpoint/showTasksResponse")
    @RequestWrapper(localName = "showTasks", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "ShowTasks")
    @ResponseWrapper(localName = "showTasksResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "ShowTasksResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.ivanov.todoproject.api.Task> showTasks(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.Session arg0
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/ITaskSOAPEndpoint/deleteTaskRequest", output = "http://api.todoproject.ivanov.ru/ITaskSOAPEndpoint/deleteTaskResponse")
    @RequestWrapper(localName = "deleteTask", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "DeleteTask")
    @ResponseWrapper(localName = "deleteTaskResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "DeleteTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.ivanov.todoproject.api.Task deleteTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        ru.ivanov.todoproject.api.Task arg1
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/ITaskSOAPEndpoint/createTaskRequest", output = "http://api.todoproject.ivanov.ru/ITaskSOAPEndpoint/createTaskResponse")
    @RequestWrapper(localName = "createTask", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "CreateTask")
    @ResponseWrapper(localName = "createTaskResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "CreateTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.ivanov.todoproject.api.Task createTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        ru.ivanov.todoproject.api.Task arg1
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/ITaskSOAPEndpoint/updateTaskRequest", output = "http://api.todoproject.ivanov.ru/ITaskSOAPEndpoint/updateTaskResponse")
    @RequestWrapper(localName = "updateTask", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "UpdateTask")
    @ResponseWrapper(localName = "updateTaskResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "UpdateTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.ivanov.todoproject.api.Task updateTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        ru.ivanov.todoproject.api.Task arg1
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/ITaskSOAPEndpoint/readTaskRequest", output = "http://api.todoproject.ivanov.ru/ITaskSOAPEndpoint/readTaskResponse")
    @RequestWrapper(localName = "readTask", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "ReadTask")
    @ResponseWrapper(localName = "readTaskResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "ReadTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.ivanov.todoproject.api.Task> readTask(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/ITaskSOAPEndpoint/getTasksByProjectRequest", output = "http://api.todoproject.ivanov.ru/ITaskSOAPEndpoint/getTasksByProjectResponse")
    @RequestWrapper(localName = "getTasksByProject", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "GetTasksByProject")
    @ResponseWrapper(localName = "getTasksByProjectResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "GetTasksByProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.ivanov.todoproject.api.Task> getTasksByProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        ru.ivanov.todoproject.api.Project arg1
    );
}
