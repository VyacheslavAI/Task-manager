package ru.ivanov.todoproject.bootstrap;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.ISessionSOAPEndpoint;
import ru.ivanov.todoproject.api.ITaskSOAPEndpoint;
import ru.ivanov.todoproject.api.IUserSOAPEndpoint;
import ru.ivanov.todoproject.command.AbstractCommand;
import ru.ivanov.todoproject.endpoint.ProjectSOAPEndpointService;
import ru.ivanov.todoproject.endpoint.SessionSOAPEndpointService;
import ru.ivanov.todoproject.endpoint.TaskSOAPEndpointService;
import ru.ivanov.todoproject.endpoint.UserSOAPEndpointService;
import ru.ivanov.todoproject.userdata.UserData;

import java.util.HashMap;
import java.util.Map;

import static ru.ivanov.todoproject.util.ConsoleHelper.*;

public class Bootstrap implements ServiceLocator {

    private IProjectSOAPEndpoint projectSOAPEndpoint = new ProjectSOAPEndpointService().getProjectSOAPEndpointPort();

    private ITaskSOAPEndpoint taskSOAPEndpoint = new TaskSOAPEndpointService().getTaskSOAPEndpointPort();

    private IUserSOAPEndpoint userSOAPEndpoint = new UserSOAPEndpointService().getUserSOAPEndpointPort();

    private ISessionSOAPEndpoint sessionSOAPEndpoint = new SessionSOAPEndpointService().getSessionSOAPEndpointPort();

    private final Map<String, AbstractCommand> commands = new HashMap<>();

    private final UserData userData = new UserData();

    public void register(final Class<?>[] commandClasses) throws IllegalAccessException, InstantiationException {
        for (final Class commandClass : commandClasses) {
            final AbstractCommand command = (AbstractCommand) commandClass.newInstance();
            command.setServiceLocator(this);
            command.setUserData(userData);
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
    public IProjectSOAPEndpoint getProjectSOAPEndpoint() {
        return projectSOAPEndpoint;
    }

    @Override
    public void setProjectSOAPEndpoint(IProjectSOAPEndpoint projectSOAPEndpoint) {
        this.projectSOAPEndpoint = projectSOAPEndpoint;
    }

    @Override
    public ITaskSOAPEndpoint getTaskSOAPEndpoint() {
        return taskSOAPEndpoint;
    }

    @Override
    public void setTaskSOAPEndpoint(ITaskSOAPEndpoint taskSOAPEndpoint) {
        this.taskSOAPEndpoint = taskSOAPEndpoint;
    }

    @Override
    public IUserSOAPEndpoint getUserSOAPEndpoint() {
        return userSOAPEndpoint;
    }

    @Override
    public void setUserSOAPEndpoint(IUserSOAPEndpoint userSOAPEndpoint) {
        this.userSOAPEndpoint = userSOAPEndpoint;
    }

    @Override
    public ISessionSOAPEndpoint getSessionSOAPEndpoint() {
        return sessionSOAPEndpoint;
    }

    @Override
    public void setSessionSOAPEndpoint(ISessionSOAPEndpoint sessionSOAPEndpoint) {
        this.sessionSOAPEndpoint = sessionSOAPEndpoint;
    }
}
