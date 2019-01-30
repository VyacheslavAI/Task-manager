package ru.ivanov.todoproject.command;

public class DataJsonSave extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "save json";
    }

    @Override
    public String getDescription() {
        return "Command for save application state in json file";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void executeCommand() {

    }
}
