package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.AuthenticationException_Exception;
import ru.ivanov.todoproject.api.InvalidArgumentException_Exception;
import ru.ivanov.todoproject.api.Session;
import ru.ivanov.todoproject.api.Task;

import static ru.ivanov.todoproject.util.ConsoleHelper.*;

import java.util.List;

public class TaskShowByProjectCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "task show";
    }

    @Override
    public String getDescription() {
        return "Print all available tasks for project";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void executeCommand() throws AuthenticationException_Exception, InvalidArgumentException_Exception {
        final Session session = userData.getSession();
        final List<Task> tasks = serviceLocator.getTaskSOAPEndpoint().showTasks(session);
        for (final Task task : tasks) {
            print(String.format("%s - %s", task.getName(), task.getId()));
        }
    }
}
