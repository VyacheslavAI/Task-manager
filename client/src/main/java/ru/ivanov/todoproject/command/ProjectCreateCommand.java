package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.api.AuthenticationException_Exception;
import ru.ivanov.todoproject.api.JsonProcessingException_Exception;
import ru.ivanov.todoproject.api.NoSuchAlgorithmException_Exception;
import ru.ivanov.todoproject.api.ObjectIsNotValidException_Exception;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.Session;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;
import static ru.ivanov.todoproject.util.ConsoleHelper.readString;

public class ProjectCreateCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "project create";
    }

    @Override
    public String getDescription() {
        return "Create new project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void executeCommand() throws NoSuchAlgorithmException_Exception, JsonProcessingException_Exception, ObjectIsNotValidException_Exception, AuthenticationException_Exception, ru.ivanov.todoproject.api.InvalidArgumentException_Exception {
        final Session session = userData.getSession();
        print("Enter project name:");
        final String projectName = readString();
        final Project project = new Project();
        project.setName(projectName);
        serviceLocator.getProjectSOAPEndpoint().createProject(session, project);
        print(String.format("Project %s has been added", projectName));
    }
}
