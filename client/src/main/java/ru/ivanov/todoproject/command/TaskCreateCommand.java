package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.Session;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;
import static ru.ivanov.todoproject.util.ConsoleHelper.readString;

public class TaskCreateCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "task create";
    }

    @Override
    public String getDescription() {
        return "Create task";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void execute() {
        final Session session = userData.getSession();
        print("Enter project task name:");
        final String projectName = readString();
    }
}
