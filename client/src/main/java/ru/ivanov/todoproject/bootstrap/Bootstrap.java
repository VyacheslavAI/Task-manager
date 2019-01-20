package ru.ivanov.todoproject.bootstrap;

import ru.ivanov.todoproject.api.SOAPServiceLocator;
import ru.ivanov.todoproject.command.Command;
import ru.ivanov.todoproject.endpoint.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.ITaskSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.IUserSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.ProjectSOAPEndpointService;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.HashMap;
import java.util.Map;

public class Bootstrap implements SOAPServiceLocator {

    private ProjectSOAPEndpointService projectSOAPEndpoint = new ProjectSOAPEndpointService();

    private P

    private final Map<String, Command> commands = new HashMap<>();

    public void register(final Class<?>[] commandClasses) throws IllegalAccessException, InstantiationException {
        for (final Class commandClass : commandClasses) {
            final Command command = (Command) commandClass.newInstance();
            final String consoleCommand = command.getConsoleCommand();
            commands.put(consoleCommand, command);
        }
    }

    public void run() {
        String welcomeString = "Welcome to Task Manager Application! \r\n" +
                "Enter \"help\" show list of available commands";
        ConsoleHelper.printMessage(welcomeString);
        String operation;
        do {
            operation = ConsoleHelper.readString();
            Command command = commands.containsKey(operation) ? commands.get(operation) : commands.get("help");
            command.execute(this);
            ConsoleHelper.printDelimiter();
        } while (!operation.equals("exit"));
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public IProjectSOAPEndpoint getProjectSOAPEndpoint() {
        return projectSOAPEndpoint;
    }

    public void setProjectSOAPEndpoint(IProjectSOAPEndpoint projectSOAPEndpoint) {
        this.projectSOAPEndpoint = projectSOAPEndpoint;
    }

    public ITaskSOAPEndpoint getTaskSOAPEndpoint() {
        return taskSOAPEndpoint;
    }

    public void setTaskSOAPEndpoint(ITaskSOAPEndpoint taskSOAPEndpoint) {
        this.taskSOAPEndpoint = taskSOAPEndpoint;
    }

    public IUserSOAPEndpoint getUserSOAPEndpoint() {
        return userSOAPEndpoint;
    }

    public void setUserSOAPEndpoint(IUserSOAPEndpoint userSOAPEndpoint) {
        this.userSOAPEndpoint = userSOAPEndpoint;
    }
}
