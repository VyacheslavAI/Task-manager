package ru.ivanov.todoproject.controller;

import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.api.ITaskService;
import ru.ivanov.todoproject.api.IUserService;
import ru.ivanov.todoproject.command.*;
import ru.ivanov.todoproject.entity.AbstractEntity;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.service.ProjectService;
import ru.ivanov.todoproject.service.TaskService;
import ru.ivanov.todoproject.service.UserService;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.*;

import static ru.ivanov.todoproject.Operation.*;

public class Controller<E extends AbstractEntity> {

    private IProjectService projectService = new ProjectService();

    private ITaskService taskService = new TaskService();

    private IUserService userService = new UserService();

    private User activeUser;

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
        commands.put(JSON_SAVE, new DataJsonSave());
        commands.put(JSON_LOAD, new DataJsonLoad());
        commands.put(XML_SAVE, new DataXmlSave());
        commands.put(XML_LOAD, new DataXmlLoad());
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

    public void filterDataForActiveUser(List<E> entities) {
        Iterator<E> iterator = entities.iterator();
        while (iterator.hasNext()) {
            E entry = iterator.next();
            if (entry.getUserId().equals(activeUser.getId())) {
                iterator.remove();
            }
        }
    }

    public static Map<String, Command> getCommands() {
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

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }
}