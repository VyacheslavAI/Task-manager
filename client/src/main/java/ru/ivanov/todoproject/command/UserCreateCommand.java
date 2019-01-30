package ru.ivanov.todoproject.command;

import javax.naming.OperationNotSupportedException;

public class UserCreateCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "user create";
    }

    @Override
    public String getDescription() {
        return "Create new user";
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
