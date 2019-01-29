package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.*;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;
import static ru.ivanov.todoproject.util.ConsoleHelper.readString;

public class ProjectReadCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "project read";
    }

    @Override
    public String getDescription() {
        return "Print information about project";
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
        print(String.format("%s - %s", projectName, project.getId()));
    }
}
