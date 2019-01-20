package ru.ivanov.todoproject.bootstrap;

import ru.ivanov.todoproject.SOAPServiceLocator;
import ru.ivanov.todoproject.command.Command;
import ru.ivanov.todoproject.endpoint.ProjectSOAPEndpointService;
import ru.ivanov.todoproject.endpoint.TaskSOAPEndpointService;
import ru.ivanov.todoproject.endpoint.UserSOAPEndpointService;
import ru.ivanov.todoproject.util.ConsoleHelper;

import java.util.HashMap;
import java.util.Map;

public class Bootstrap implements SOAPServiceLocator {

    private ProjectSOAPEndpointService projectSOAPEndpoint = new ProjectSOAPEndpointService();

    private TaskSOAPEndpointService taskSOAPEndpoint = new TaskSOAPEndpointService();

    private UserSOAPEndpointService userSOAPEndpoint = new UserSOAPEndpointService();

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
}
