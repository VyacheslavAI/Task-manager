package ru.ivanov.todoproject.bootstrap;

import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.api.ITaskService;
import ru.ivanov.todoproject.api.IUserService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.command.Command;
import ru.ivanov.todoproject.service.ProjectService;
import ru.ivanov.todoproject.service.TaskService;
import ru.ivanov.todoproject.service.UserService;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.HashMap;
import java.util.Map;

public class Bootstrap implements ServiceLocator {

    private IProjectService projectService = new ProjectService();

    private ITaskService taskService = new TaskService();

    private IUserService userService = new UserService();

    private final Map<String, Command> commands = new HashMap<>();

    public void register(final Class<?>[] commandClasses) {
        try {
            for (Class commandClass : commandClasses) {
                final Command command = (Command) commandClass.newInstance();
                final String consoleCommand = command.getConsoleCommand();
                commands.put(consoleCommand, command);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            ConsoleHelper.printMessage("An error has occurred during commands registration");
        }
    }

    private void loadData() {
        commands.get("load bin").execute(this);
    }

    public void run() {
        userService.adminInitialization();
        loadData();
        String welcomeString = "Welcome to Task Manager Application! \r\n" +
                "Enter \"help\" show list of available commands";
        ConsoleHelper.printMessage(welcomeString);
        String operation;
        do {
            operation = ConsoleHelper.readString();
            Command command = commands.containsKey(operation) ? commands.get(operation) : commands.get("help");
            boolean hasAuthorizedUser = userService.hasUserAuthorized();
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

    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    public ITaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(ITaskService taskService) {
        this.taskService = taskService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}