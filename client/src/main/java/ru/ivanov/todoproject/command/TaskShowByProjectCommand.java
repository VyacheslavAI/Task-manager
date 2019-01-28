package ru.ivanov.todoproject.command;

public class TaskShowByProjectCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "task show";
    }

    @Override
    public String getDescription() {
        return "Print all available tasks for project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() {

    }
}
