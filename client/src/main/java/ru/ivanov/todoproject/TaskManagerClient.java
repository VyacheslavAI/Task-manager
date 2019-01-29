package ru.ivanov.todoproject;

import ru.ivanov.todoproject.api.AuthorizationException_Exception;
import ru.ivanov.todoproject.api.InvalidArgumentException_Exception;
import ru.ivanov.todoproject.api.JsonProcessingException_Exception;
import ru.ivanov.todoproject.api.NoSuchAlgorithmException_Exception;
import ru.ivanov.todoproject.api.ObjectIsNotValidException_Exception;
import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.command.*;

import java.security.NoSuchAlgorithmException;

public class TaskManagerClient {

    private final static Class<?>[] commands = {ExitCommand.class,
            HelpCommand.class, ProjectCreateCommand.class, ProjectReadCommand.class,
            ProjectUpdateCommand.class, ProjectDeleteCommand.class, ProjectShowCommand.class,
            TaskCreateCommand.class, TaskReadCommand.class, TaskUpdateCommand.class,
            TaskDeleteCommand.class, TaskShowByProjectCommand.class, UserCreateCommand.class,
            UserReadCommand.class, UserUpdateCommand.class, UserDeleteCommand.class, SignInCommand.class,
            SignOutCommand.class, UserShowCommand.class, RegistryCommand.class};

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, JsonProcessingException_Exception, ObjectIsNotValidException_Exception, AuthorizationException_Exception, NoSuchAlgorithmException_Exception, ru.ivanov.todoproject.api.ObjectNotFoundException_Exception, InvalidArgumentException_Exception, ru.ivanov.todoproject.api.AuthenticationException_Exception, NoSuchAlgorithmException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.register(commands);
        bootstrap.run();
    }
}