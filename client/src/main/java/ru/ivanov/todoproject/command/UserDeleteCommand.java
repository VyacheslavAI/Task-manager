package ru.ivanov.todoproject.command;

public class UserDeleteCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "user delete";
    }

    @Override
    public String getDescription() {
        return "Delete user";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() {

    }
}
