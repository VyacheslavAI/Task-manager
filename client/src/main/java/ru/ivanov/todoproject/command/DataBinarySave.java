package ru.ivanov.todoproject.command;

public class DataBinarySave extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "save bin";
    }

    @Override
    public String getDescription() {
        return "Command for save application state in binary file";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void executeCommand() {

    }
}
