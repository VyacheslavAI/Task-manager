package ru.ivanov.todoproject.command;

import javax.naming.OperationNotSupportedException;

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
    public void executeCommand() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
}
