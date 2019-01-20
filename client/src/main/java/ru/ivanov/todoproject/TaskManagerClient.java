package ru.ivanov.todoproject;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.command.*;

public class TaskManagerClient {

    private final static Class<?>[] commands = {ExitCommand.class,
            HelpCommand.class, ProjectCreateCommand.class, ProjectReadCommand.class,
            ProjectUpdateCommand.class, ProjectDeleteCommand.class, ProjectShowCommand.class,
            TaskCreateCommand.class, TaskReadCommand.class, TaskUpdateCommand.class,
            TaskDeleteCommand.class, TaskShowByProjectCommand.class, UserCreateCommand.class,
            UserReadCommand.class, UserUpdateCommand.class, UserDeleteCommand.class, SignInCommand.class,
            SignOutCommand.class, UserShowCommand.class};

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.register(commands);
        bootstrap.run();
    }
}
