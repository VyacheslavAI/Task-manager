package ru.ivanov.todoproject;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.command.*;

public class TaskManager {

    private static Class<?>[] commands = {DataBinaryLoad.class, DataBinarySave.class, DataJsonSave.class,
            DataJsonLoad.class, DataXmlLoad.class, DataXmlSave.class, ExitCommand.class,
            HelpCommand.class, ProjectCreateCommand.class, ProjectReadCommand.class,
            ProjectUpdateCommand.class, ProjectDeleteCommand.class, ProjectShowCommand.class,
            TaskCreateCommand.class, TaskReadCommand.class, TaskUpdateCommand.class,
            TaskDeleteCommand.class, TaskShowByProjectCommand.class, UserCreateCommand.class,
            UserReadCommand.class, UserUpdateCommand.class, UserDeleteCommand.class, SignInCommand.class,
            SignOutCommand.class, UserShowCommand.class};

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.register(commands);
        bootstrap.run();
    }
}
