package ru.ivanov.todoproject.bootstrap;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.command.Command;
import ru.ivanov.todoproject.endpoint.ProjectSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.SessionSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.TaskSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.UserSOAPEndpoint;
import ru.ivanov.todoproject.service.ProjectService;
import ru.ivanov.todoproject.service.SessionService;
import ru.ivanov.todoproject.service.TaskService;
import ru.ivanov.todoproject.service.UserService;
import ru.ivanov.todoproject.util.ConsoleHelper;

import javax.xml.ws.Endpoint;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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

    private final Map<String, Command> commands = new HashMap<>();

    public void register(final Class<?>[] commandClasses) throws IllegalAccessException, InstantiationException {
        for (Class commandClass : commandClasses) {
            final Command command = (Command) commandClass.newInstance();
            final String consoleCommand = command.getConsoleCommand();
            commands.put(consoleCommand, command);
        }
    }

    private void loadData() {
        commands.get("load bin").execute(this);
    }

    public void run() throws JsonProcessingException, NoSuchAlgorithmException {
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

    public Map<String, Command> getCommands() {
        return commands;
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