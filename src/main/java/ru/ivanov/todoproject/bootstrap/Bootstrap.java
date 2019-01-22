package ru.ivanov.todoproject.bootstrap;

import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.command.Command;
import ru.ivanov.todoproject.service.ProjectService;
import ru.ivanov.todoproject.service.SessionService;
import ru.ivanov.todoproject.service.TaskService;
import ru.ivanov.todoproject.service.UserService;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.*;

public class Bootstrap implements ServiceLocator {

    private IProjectService projectService = new ProjectService();

    private ITaskService taskService = new TaskService();

    private IUserService userService = new UserService();

    private ISessionService sessionService = new SessionService();

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

    public void run() {
        loadData();
        userService.adminInitialization();
        String welcomeString = "Welcome to Task Manager Application! \r\n" +
                "Enter \"help\" show list of available commands";
        ConsoleHelper.printMessage(welcomeString);
        String operation;
        do {
            final boolean hasAuthorizedUser = userService.hasUserAuthorized();
            operation = ConsoleHelper.readString();
            Command command = commands.containsKey(operation) ? commands.get(operation) : commands.get("help");
            if (command.isAuthorizationRequired() && !hasAuthorizedUser) {
                command = commands.get("help");
            }
            command.execute(this);
            ConsoleHelper.printDelimiter();
        } while (!operation.equals("exit"));
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
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}