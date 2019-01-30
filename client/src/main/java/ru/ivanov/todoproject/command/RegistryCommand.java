package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.ObjectIsNotValidException_Exception;

import java.security.NoSuchAlgorithmException;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;
import static ru.ivanov.todoproject.util.ConsoleHelper.readString;

public class RegistryCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "registry";
    }

    @Override
    public String getDescription() {
        return "New user registration";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return false;
    }

    @Override
    public void execute() throws ObjectIsNotValidException_Exception, NoSuchAlgorithmException {
        print("Enter new login:");
        final String login = readString();
        print("Enter password:");
        final String password = readString();
        final String passwordHash = securityClientManager.getPasswordHash(password);
        final boolean isRegisterSuccess = serviceLocator.getSessionSOAPEndpoint().userRegistry(login, passwordHash);
        if (isRegisterSuccess) {
            print(String.format("User %s registered successfully", login));
            return;
        }
        print("Registration failed");
    }
}
