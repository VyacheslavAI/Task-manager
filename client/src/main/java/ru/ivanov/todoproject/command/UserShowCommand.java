package ru.ivanov.todoproject.command;

import javax.naming.OperationNotSupportedException;

public class UserShowCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "user show";
    }

    @Override
    public String getDescription() {
        return "Print information about all available users";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void executeCommand() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
}
