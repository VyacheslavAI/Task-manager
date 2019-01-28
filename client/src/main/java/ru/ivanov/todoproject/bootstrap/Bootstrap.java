package ru.ivanov.todoproject.bootstrap;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.UserData;
import ru.ivanov.todoproject.command.AbstractCommand;
import ru.ivanov.todoproject.endpoint.ProjectSOAPEndpointService;
import ru.ivanov.todoproject.endpoint.SessionSOAPEndpointService;
import ru.ivanov.todoproject.endpoint.TaskSOAPEndpointService;
import ru.ivanov.todoproject.endpoint.UserSOAPEndpointService;

import java.util.HashMap;
import java.util.Map;

import static ru.ivanov.todoproject.util.ConsoleHelper.*;

public class Bootstrap implements ServiceLocator {

    private ProjectSOAPEndpointService projectSOAPEndpoint = new ProjectSOAPEndpointService();

    private TaskSOAPEndpointService taskSOAPEndpoint = new TaskSOAPEndpointService();

    private UserSOAPEndpointService userSOAPEndpoint = new UserSOAPEndpointService();

    private SessionSOAPEndpointService sessionSOAPEndpoint = new SessionSOAPEndpointService();

    private UserData userData = new UserData();

    private final Map<String, AbstractCommand> commands = new HashMap<>();

    public void register(final Class<?>[] commandClasses) throws IllegalAccessException, InstantiationException {
        for (final Class commandClass : commandClasses) {
            final AbstractCommand command = (AbstractCommand) commandClass.newInstance();
            command.setServiceLocator(this);
            final String consoleCommand = command.getConsoleCommand();
            commands.put(consoleCommand, command);
        }
    }

    public void run() {
        printWelcome();
        String operation;
        do {
            operation = readString();
            AbstractCommand command = commands.get(operation);
            if (command == null) command = commands.get("help");
            if (command.isAuthorizationRequired() && !userData.isUserAuthorized()) command = commands.get("help");
            command.execute();
            printDelimiter();
        } while (!operation.equals("exit"));
    }

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    @Override
    public ProjectSOAPEndpointService getProjectSOAPEndpointService() {
        return projectSOAPEndpoint;
    }

    @Override
    public void setProjectSOAPEndpointService(ProjectSOAPEndpointService projectSOAPEndpoint) {
        this.projectSOAPEndpoint = projectSOAPEndpoint;
    }

    @Override
    public TaskSOAPEndpointService getTaskSOAPEndpointService() {
        return taskSOAPEndpoint;
    }

    @Override
    public void setTaskSOAPEndpointService(TaskSOAPEndpointService taskSOAPEndpoint) {
        this.taskSOAPEndpoint = taskSOAPEndpoint;
    }

    @Override
    public UserSOAPEndpointService getUserSOAPEndpointService() {
        return userSOAPEndpoint;
    }

    @Override
    public void setUserSOAPEndpointService(UserSOAPEndpointService userSOAPEndpoint) {
        this.userSOAPEndpoint = userSOAPEndpoint;
    }

    @Override
    public SessionSOAPEndpointService getSessionSOAPEndpointService() {
        return sessionSOAPEndpoint;
    }

    @Override
    public void setSessionSOAPEndpointService(SessionSOAPEndpointService sessionSOAPEndpoint) {
        this.sessionSOAPEndpoint = sessionSOAPEndpoint;
    }

    @Override
    public UserData getUserData() {
        return userData;
    }

    @Override
    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
