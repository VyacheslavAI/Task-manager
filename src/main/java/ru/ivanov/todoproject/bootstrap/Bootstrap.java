package ru.ivanov.todoproject.bootstrap;

import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.api.ITaskService;
import ru.ivanov.todoproject.api.IUserService;
import ru.ivanov.todoproject.command.Command;
import ru.ivanov.todoproject.service.ProjectService;
import ru.ivanov.todoproject.service.TaskService;
import ru.ivanov.todoproject.service.UserService;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.*;

public class Bootstrap {

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

    private void execute(String operation) {
        operation = commands.containsKey(operation) ? operation : "help";
        commands.get(operation).execute(this);
    }

    public void run() {
        String welcomeString = "Welcome to Task Manager Application! \r\n" +
                "Enter \"help\" show list of available commands";
        ConsoleHelper.printMessage(welcomeString);
        String operation;
        do {
            operation = ConsoleHelper.readString();
            execute(operation);
            ConsoleHelper.printDelimiter();
        } while (!operation.equals("exit"));
    }

    public void clearAllProjectAndTask() {
        projectService.deleteAllProject();
        taskService.deleteAllTask();
    }

    public List<Command> getListAvailableCommands() {
        final boolean hasAuthorizedUser = userService.hasUserAuthorized();
        final List<Command> availableCommands = new ArrayList<>(commands.values());
        final Iterator<Command> commandIterator = availableCommands.iterator();
        while (commandIterator.hasNext()) {
            final Command command = commandIterator.next();
            if (command.isAuthorizationRequired() && !hasAuthorizedUser) {
                commandIterator.remove();
            }
        }
        return availableCommands;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public ITaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}