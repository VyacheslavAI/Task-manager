package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.*;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;
import static ru.ivanov.todoproject.util.ConsoleHelper.readString;

public class TaskReadCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "task read";
    }

    @Override
    public String getDescription() {
        return "Print information about available tasks for project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() throws InvalidArgumentException_Exception, ObjectIsNotValidException_Exception, ObjectNotFoundException_Exception, AuthenticationException_Exception {
        final Session session = userData.getSession();
        print("Enter project name:");
        final String projectName = readString();
        final Project project = serviceLocator.getProjectSOAPEndpoint().readProject(session, projectName);
        print("Enter task name:");
        final String taskName = readString();
        final Task task = serviceLocator.getTaskSOAPEndpoint().readTask(session, project, taskName);
        print(String.format("%s - %s", task.getName(), task.getId()));
    }
}
