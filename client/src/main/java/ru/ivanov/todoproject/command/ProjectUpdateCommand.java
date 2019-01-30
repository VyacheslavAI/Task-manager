package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.api.AuthenticationException_Exception;
import ru.ivanov.todoproject.api.InvalidArgumentException_Exception;
import ru.ivanov.todoproject.api.ObjectIsNotValidException_Exception;
import ru.ivanov.todoproject.api.ObjectNotFoundException_Exception;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.Session;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;
import static ru.ivanov.todoproject.util.ConsoleHelper.readString;

public class ProjectUpdateCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "project edit";
    }

    @Override
    public String getDescription() {
        return "Edit project";
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
        final Project project = serviceLocator.getProjectSOAPEndpoint().readProject(session, projectName);
        print("Enter new project name:");
        final String newProjectName = readString();
        project.setName(newProjectName);
        serviceLocator.getProjectSOAPEndpoint().updateProject(session, project);
        print(String.format("Project %s has been updated", newProjectName));
    }
}
