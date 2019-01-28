package ru.ivanov.todoproject.command;

public class TaskReadCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "task read";
    }

    @Override
    public String getDescription() {
        return "Print information about available tasks for project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() {

    }
}
