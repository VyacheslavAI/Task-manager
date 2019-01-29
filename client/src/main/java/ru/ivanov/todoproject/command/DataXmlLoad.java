package ru.ivanov.todoproject.command;

public class DataXmlLoad extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "load xml";
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
    public void execute() {

    }
}
