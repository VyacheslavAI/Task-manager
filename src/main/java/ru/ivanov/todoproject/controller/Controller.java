package ru.ivanov.todoproject.controller;

import ru.ivanov.todoproject.command.*;
import ru.ivanov.todoproject.service.ProjectService;
import ru.ivanov.todoproject.service.TaskService;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.HashMap;
import java.util.Map;

import static ru.ivanov.todoproject.Operation.*;

public class Controller {

    private ProjectService projectService = new ProjectService();

    private TaskService taskService = new TaskService();

    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put(HELP, new HelpCommand());
        commands.put(PROJECT_CREATE, new ProjectCreateCommand());
        commands.put(PROJECT_READ, new ProjectReadCommand());
        commands.put(PROJECT_UPDATE, new ProjectUpdateCommand());
        commands.put(PROJECT_DELETE, new ProjectDeleteCommand());
        commands.put(TASK_CREATE, new TaskCreateCommand());
        commands.put(TASK_READ, new TaskReadCommand());
        commands.put(TASK_UPDATE, new TaskUpdateCommand());
        commands.put(TASK_DELETE, new TaskDeleteCommand());
        commands.put(TASK_SHOW, new TaskShowByProjectCommand());
        commands.put(PROJECT_SHOW, new ProjectShowCommand());
        commands.put(BIN_SAVE, new DataBinarySave());
        commands.put(BIN_LOAD, new DataBinaryLoad());
        commands.put(EXIT, new ExitCommand());
    }

    public void execute(String operation) {
        operation = commands.containsKey(operation) ? operation : "help";
        commands.get(operation).execute(this);
    }

    public void run() {
        ConsoleHelper.printMessage("Welcome to Task Manager Application! \r\n" +
                "Enter \"help\" show list of available commands");

        String operation;
        do {
            operation = ConsoleHelper.readString();
            execute(operation);
            ConsoleHelper.printDelimiter();
        } while (!operation.equals(EXIT));
    }

    public void clearAllEntity() {
        projectService.deleteAllProject();
        taskService.deleteAllTask();
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public static Map<String, Command> getCommands() {
        return commands;
    }
}