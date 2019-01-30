package ru.ivanov.todoproject.command;

import javax.naming.OperationNotSupportedException;

public class UserReadCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "user read";
    }

    @Override
    public String getDescription() {
        return "Print information about user";
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
