package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.AuthenticationException_Exception;
import ru.ivanov.todoproject.api.ObjectIsNotValidException_Exception;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class SignOutCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "logout";
    }

    @Override
    public String getDescription() {
        return "Log Out";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return true;
    }

    @Override
    public void executeCommand() throws ObjectIsNotValidException_Exception, AuthenticationException_Exception {
        serviceLocator.getSessionSOAPEndpoint().logout(userData.getSession());
        userData.setSession(null);
        ConsoleHelper.print("Log Out successful");
    }
}
