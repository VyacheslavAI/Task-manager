package ru.ivanov.todoproject.command;

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
    public void execute() {
    }
}
