package ru.ivanov.todoproject.command;

public class DataBinaryLoad extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "load bin";
    }

    @Override
    public String getDescription() {
        return "Command for load application state from binary file";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void executeCommand() {

    }
}
