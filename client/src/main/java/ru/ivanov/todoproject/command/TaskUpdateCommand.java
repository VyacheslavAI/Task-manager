package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.api.AuthenticationException_Exception;
import ru.ivanov.todoproject.api.InvalidArgumentException_Exception;
import ru.ivanov.todoproject.api.ObjectIsNotValidException_Exception;
import ru.ivanov.todoproject.api.ObjectNotFoundException_Exception;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.api.Task;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;
import static ru.ivanov.todoproject.util.ConsoleHelper.readString;

public class TaskUpdateCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "edit task";
    }

    @Override
    public String getDescription() {
        return "Edit task for select project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() throws AuthenticationException_Exception, ObjectNotFoundException_Exception, InvalidArgumentException_Exception, ObjectIsNotValidException_Exception {
        final Session session = userData.getSession();
        print("Enter project name:");
        final String projectName = readString();
        final Project project = serviceLocator.getProjectSOAPEndpoint().readProject(session, projectName);
        final String taskName = readString();
        final Task task = serviceLocator.getTaskSOAPEndpoint().readTask(session, project, taskName);
        print("Enter new name:");
        final String newTaskName = readString();
        task.setName(newTaskName);
        serviceLocator.getTaskSOAPEndpoint().updateTask(session, task);
        print(String.format("Task %s has been updated", newTaskName));
    }
}
