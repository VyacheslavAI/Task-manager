package ru.ivanov.todoproject.command;

public class DataXmlSave extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "save xml";
    }

    @Override
    public String getDescription() {
        return "Command to save application state in xml file";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() {
    }
}
