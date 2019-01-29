package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.*;

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
    public void execute() throws NoSuchAlgorithmException_Exception, RequestNotAuthenticatedException_Exception, JsonProcessingException_Exception, ObjectIsNotValidException_Exception {
        final Session session = userData.getSession();
        print("Enter project name:");
        final String projectName = readString();
        final Project project = new Project();
        project.setName(projectName);
        project.setUserId(userData.getSession().getUserId());
        serviceLocator.getProjectSOAPEndpoint().createProject(session, project);
        print(String.format("Project %s has been added", projectName));
    }
}
