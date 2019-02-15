package ru.ivanov.todoproject.bootstrap;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.dto.Serializer;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;

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
    private Serializer serializer;

    private void userInitialization() throws InvalidArgumentException, NoSuchAlgorithmException, ObjectIsNotValidException, JsonProcessingException, ObjectNotFoundException {
        userService.userInitialize("admin", "admin");
        userService.userInitialize("root", "root");
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