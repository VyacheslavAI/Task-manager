package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.Session;

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
    public void execute() {
        final Session session = userData.getSession();

    }
}
