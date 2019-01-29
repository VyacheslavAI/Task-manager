package ru.ivanov.todoproject.command;

public class DataJsonLoad extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "load json";
    }

    @Override
    public String getDescription() {
        return "Command for load application state from json file";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() {
    }
}
