package ru.ivanov.todoproject.command;

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
    public void execute() {
    }
}
