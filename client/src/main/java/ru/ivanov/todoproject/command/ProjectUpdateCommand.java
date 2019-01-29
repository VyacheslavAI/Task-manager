package ru.ivanov.todoproject.command;

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
    public void execute() {
        final Session session = userData.getSession();
        print("Enter new project name:");
        final String newProjectName = readString();
        serviceLocator.getProjectSOAPEndpoint().updateProject(session, );
    }
}
