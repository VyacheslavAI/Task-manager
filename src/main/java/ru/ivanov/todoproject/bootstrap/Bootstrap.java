package ru.ivanov.todoproject.bootstrap;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.dto.Domain;
import ru.ivanov.todoproject.endpoint.ProjectSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.SessionSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.TaskSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.UserSOAPEndpoint;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.service.ProjectService;
import ru.ivanov.todoproject.service.SessionService;
import ru.ivanov.todoproject.service.TaskService;
import ru.ivanov.todoproject.service.UserService;
import ru.ivanov.todoproject.util.ConsoleHelper;

import javax.xml.ws.Endpoint;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

public class Bootstrap implements ServiceLocator {

    private IProjectService projectService = new ProjectService();

    private ITaskService taskService = new TaskService();

    private IUserService userService = new UserService();

    private ISessionService sessionService = new SessionService();

    {
        projectService.setServiceLocator(this);
        taskService.setServiceLocator(this);
        userService.setServiceLocator(this);
        sessionService.setServiceLocator(this);
    }

    private void loadData() {
        try (final InputStream inputStream = Files.newInputStream(Paths.get("data.bin"));
             final ObjectInput objectInput = new ObjectInputStream(inputStream)) {
            projectService.deleteAllProject();
            taskService.deleteAllTask();
            final Domain domain = (Domain) objectInput.readObject();
            domain.loadFromDomain(this);
            ConsoleHelper.printMessage("Loading from binary file was successful");
        } catch (Exception e) {
            ConsoleHelper.printMessage("An error has occurred during loading from binary file");
        }
    }

    public void run() throws JsonProcessingException, NoSuchAlgorithmException, ObjectIsNotValidException {
        loadData();
        userService.userInitialize("admin", "admin");
        userService.userInitialize("root", "root");

        ProjectSOAPEndpoint projectSOAPEndpoint = new ProjectSOAPEndpoint();
        TaskSOAPEndpoint taskSOAPEndpoint = new TaskSOAPEndpoint();
        UserSOAPEndpoint userSOAPEndpoint = new UserSOAPEndpoint();
        SessionSOAPEndpoint sessionSOAPEndpoint = new SessionSOAPEndpoint();

        projectSOAPEndpoint.setServiceLocator(this);
        taskSOAPEndpoint.setServiceLocator(this);
        userSOAPEndpoint.setServiceLocator(this);
        sessionSOAPEndpoint.setServiceLocator(this);

        Endpoint.publish("http://localhost/8080/project", projectSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/task", taskSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/user", userSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/session", sessionSOAPEndpoint);
    }

    @Override
    public IProjectService getProjectService() {
        return projectService;
    }

    @Override
    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ITaskService getTaskService() {
        return taskService;
    }

    @Override
    public void setTaskService(ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public IUserService getUserService() {
        return userService;
    }

    @Override
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ISessionService getSessionService() {
        return sessionService;
    }

    @Override
    public void setSessionService(ISessionService ISessionService) {
        this.sessionService = ISessionService;
    }
}