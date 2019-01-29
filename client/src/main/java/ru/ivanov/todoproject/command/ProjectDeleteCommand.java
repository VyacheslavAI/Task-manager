package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.RequestNotAuthenticatedException_Exception;
import ru.ivanov.todoproject.api.Session;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;
import static ru.ivanov.todoproject.util.ConsoleHelper.readString;

public class ProjectDeleteCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "delete project";
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
    public void execute() throws RequestNotAuthenticatedException_Exception {
        final Session session = userData.getSession();
        print("Enter project name:");
        final String projectName = readString();
        final Project project = serviceLocator.getProjectSOAPEndpoint().readProject(session, projectName);
        
    }
}
