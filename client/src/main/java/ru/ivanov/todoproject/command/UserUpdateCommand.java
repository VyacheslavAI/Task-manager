package ru.ivanov.todoproject.command;

public class UserUpdateCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "user edit";
    }

    @Override
    public String getDescription() {
        return "Update user";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() {

    }
}
