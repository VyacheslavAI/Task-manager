package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.api.AuthenticationException_Exception;
import ru.ivanov.todoproject.api.InvalidArgumentException_Exception;
import ru.ivanov.todoproject.api.ObjectIsNotValidException_Exception;
import ru.ivanov.todoproject.api.ObjectNotFoundException_Exception;
import ru.ivanov.todoproject.api.Session;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;
import static ru.ivanov.todoproject.util.ConsoleHelper.readString;

public class ProjectDeleteCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "project delete";
    }

    @Override
    public String getDescription() {
        return "Command for delete project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void executeCommand() throws InvalidArgumentException_Exception, ObjectIsNotValidException_Exception, ObjectNotFoundException_Exception, AuthenticationException_Exception {
        final Session session = userData.getSession();
        print("Enter project name:");
        final String projectName = readString();
        serviceLocator.getProjectSOAPEndpoint().deleteProject(session, projectName);
        print(String.format("Project %s has been deleted", projectName));
    }
}
