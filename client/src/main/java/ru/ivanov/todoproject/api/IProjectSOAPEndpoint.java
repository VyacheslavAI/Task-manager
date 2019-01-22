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
 * 2019-01-22T17:50:51.869+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://api.todoproject.ivanov.ru/", name = "IProjectSOAPEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface IProjectSOAPEndpoint {

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/IProjectSOAPEndpoint/createProjectRequest", output = "http://api.todoproject.ivanov.ru/IProjectSOAPEndpoint/createProjectResponse")
    @RequestWrapper(localName = "createProject", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "CreateProject")
    @ResponseWrapper(localName = "createProjectResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "CreateProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.ivanov.todoproject.api.Project createProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        ru.ivanov.todoproject.api.Project arg1
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/IProjectSOAPEndpoint/readProjectRequest", output = "http://api.todoproject.ivanov.ru/IProjectSOAPEndpoint/readProjectResponse")
    @RequestWrapper(localName = "readProject", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "ReadProject")
    @ResponseWrapper(localName = "readProjectResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "ReadProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.ivanov.todoproject.api.Project> readProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/IProjectSOAPEndpoint/showProjectsRequest", output = "http://api.todoproject.ivanov.ru/IProjectSOAPEndpoint/showProjectsResponse")
    @RequestWrapper(localName = "showProjects", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "ShowProjects")
    @ResponseWrapper(localName = "showProjectsResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "ShowProjectsResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.ivanov.todoproject.api.Project> showProjects(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.Session arg0
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/IProjectSOAPEndpoint/deleteProjectRequest", output = "http://api.todoproject.ivanov.ru/IProjectSOAPEndpoint/deleteProjectResponse")
    @RequestWrapper(localName = "deleteProject", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "DeleteProject")
    @ResponseWrapper(localName = "deleteProjectResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "DeleteProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.ivanov.todoproject.api.Project deleteProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        ru.ivanov.todoproject.api.Project arg1
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/IProjectSOAPEndpoint/updateProjectRequest", output = "http://api.todoproject.ivanov.ru/IProjectSOAPEndpoint/updateProjectResponse")
    @RequestWrapper(localName = "updateProject", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "UpdateProject")
    @ResponseWrapper(localName = "updateProjectResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "UpdateProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.ivanov.todoproject.api.Project updateProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        ru.ivanov.todoproject.api.Project arg1
    );
}