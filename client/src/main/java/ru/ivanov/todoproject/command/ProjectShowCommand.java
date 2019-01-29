package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.AuthenticationException_Exception;
import ru.ivanov.todoproject.api.InvalidArgumentException_Exception;
import ru.ivanov.todoproject.api.Project;
import ru.ivanov.todoproject.api.Session;

import java.util.List;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;

public class ProjectShowCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "project show";
    }

    @Override
    public String getDescription() {
        return "Print information about all available projects";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() throws AuthenticationException_Exception, InvalidArgumentException_Exception {
        final Session session = userData.getSession();
        final List<Project> allUserProject = serviceLocator.getProjectSOAPEndpoint().showProjects(session);
        for (Project project : allUserProject) {
            print(String.format("%s - %s", project.getName(), project.getId()));
        }
    }
}
