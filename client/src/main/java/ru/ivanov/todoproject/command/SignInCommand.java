package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.*;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;
import static ru.ivanov.todoproject.util.ConsoleHelper.readString;

public class SignInCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "login";
    }

    @Override
    public String getDescription() {
        return "Log In";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return false;
    }

    @Override
    public void execute() throws ObjectIsNotValidException_Exception, AuthorizationException_Exception, ObjectNotFoundException_Exception, InvalidArgumentException_Exception {
        print("Enter login:");
        final String login = readString();
        print("Enter password:");
        final String password = readString();
        final Session session = serviceLocator.getSessionSOAPEndpoint().login(login, password);
        userData.setSession(session);
        print("Log In successful");
    }
}
