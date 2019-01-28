package ru.ivanov.todoproject.bootstrap;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.endpoint.ProjectSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.SessionSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.TaskSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.UserSOAPEndpoint;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.service.ProjectService;
import ru.ivanov.todoproject.service.SessionService;
import ru.ivanov.todoproject.service.TaskService;
import ru.ivanov.todoproject.service.UserService;

import javax.xml.ws.Endpoint;
import java.security.NoSuchAlgorithmException;

import static ru.ivanov.todoproject.dto.Domain.loadApplicationDataFromBinary;

public class Bootstrap implements ServiceLocator {

    private IProjectService projectService = new ProjectService();

    private ITaskService taskService = new TaskService();

    private IUserService userService = new UserService();

    private ISessionService sessionService = new SessionService();

    private ProjectSOAPEndpoint projectSOAPEndpoint = new ProjectSOAPEndpoint();

    private TaskSOAPEndpoint taskSOAPEndpoint = new TaskSOAPEndpoint();

    private UserSOAPEndpoint userSOAPEndpoint = new UserSOAPEndpoint();

    private SessionSOAPEndpoint sessionSOAPEndpoint = new SessionSOAPEndpoint();

    {
        projectService.setServiceLocator(this);
        taskService.setServiceLocator(this);
        userService.setServiceLocator(this);
        sessionService.setServiceLocator(this);
        projectSOAPEndpoint.setServiceLocator(this);
        taskSOAPEndpoint.setServiceLocator(this);
        userSOAPEndpoint.setServiceLocator(this);
        sessionSOAPEndpoint.setServiceLocator(this);
    }

    public void run() throws JsonProcessingException, NoSuchAlgorithmException, ObjectIsNotValidException, InvalidArgumentException {
        loadApplicationDataFromBinary(this);
        userService.userInitialize("admin", "admin");
        Endpoint.publish("http://localhost/8080/project", projectSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/task", taskSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/user", userSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/session", sessionSOAPEndpoint);
    }

    @Override
    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ITaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ISessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(ISessionService ISessionService) {
        this.sessionService = ISessionService;
    }
}