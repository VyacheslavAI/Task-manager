package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.SOAPServiceLocator;

public abstract class Command {

    private static final String warningString = "Several projects found with data name. \n" +
            "Select the creation date of the desired project.";

    public abstract String getConsoleCommand();

    public abstract String getDescription();

    public abstract void execute(final SOAPServiceLocator soapServiceLocator);
}
