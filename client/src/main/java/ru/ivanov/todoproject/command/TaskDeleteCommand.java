package ru.ivanov.todoproject.command;

public class TaskDeleteCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "task delete";
    }

    @Override
    public String getDescription() {
        return "Delete task for select project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() {

    }
}
