package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.api.AuthenticationException_Exception;
import ru.ivanov.todoproject.api.InvalidArgumentException_Exception;
import ru.ivanov.todoproject.api.ObjectNotFoundException_Exception;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.Session;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;
import static ru.ivanov.todoproject.util.ConsoleHelper.readString;

public class TaskDeleteCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "task delete";
    }

    @Override
    public String getDescription() {
        return "Delete task for select project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void executeCommand() throws AuthenticationException_Exception, ObjectNotFoundException_Exception, InvalidArgumentException_Exception {
        final Session session = userData.getSession();
        print("Enter project name:");
        final String projectName = readString();
        final Project project = serviceLocator.getProjectSOAPEndpoint().readProject(session, projectName);
        print("Enter task name:");
        final String taskName = readString();
        serviceLocator.getTaskSOAPEndpoint().deleteTask(session, project.getId(), taskName);
        print(String.format("Task %s has been deleted", taskName));
    }
}
