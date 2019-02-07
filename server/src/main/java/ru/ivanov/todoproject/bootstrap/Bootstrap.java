package ru.ivanov.todoproject.bootstrap;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.dto.Serializer;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;

@ApplicationScoped
public class Bootstrap implements ServiceLocator {

    @Inject
    private IProjectService projectService;

    @Inject
    private ITaskService taskService;

    @Inject
    private IUserService userService;

    @Inject
    private ISessionService sessionService;

    @Inject
    private IProjectSOAPEndpoint projectSOAPEndpoint;

    @Inject
    private ITaskSOAPEndpoint taskSOAPEndpoint;

    @Inject
    private IUserSOAPEndpoint userSOAPEndpoint;

    @Inject
    private ISessionSOAPEndpoint sessionSOAPEndpoint;

    @Inject
    private Serializer serializer;

    public void run() throws JsonProcessingException, NoSuchAlgorithmException, ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException {
        userInitialization();
        Endpoint.publish("http://localhost/8080/project", projectSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/task", taskSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/user", userSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/session", sessionSOAPEndpoint);
        print("Server started successfully");
    }

    private void userInitialization() throws InvalidArgumentException, NoSuchAlgorithmException, ObjectIsNotValidException, JsonProcessingException, ObjectNotFoundException {
        userService.userInitialize("admin", "admin");
        userService.userInitialize("root", "root");
        print("Users initialized successfully");
    }

    @Override
    public IUserService getUserService() {
        return userService;
    }

    @Override
    public IProjectService getProjectService() {
        return projectService;
    }

    @Override
    public ITaskService getTaskService() {
        return taskService;
    }

    @Override
    public ISessionService getSessionService() {
        return sessionService;
    }
}