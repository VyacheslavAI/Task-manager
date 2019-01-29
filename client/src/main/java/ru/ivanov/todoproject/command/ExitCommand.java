package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.api.AuthenticationException_Exception;
import ru.ivanov.todoproject.api.ObjectIsNotValidException_Exception;
import ru.ivanov.todoproject.util.ConsoleHelper;

public class ExitCommand extends AbstractCommand {

    @Override
    public String getConsoleCommand() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Exit";
    }

    @Override
    public boolean isAuthorizationRequired() {
        return false;
    }

    @Override
    public void execute() throws ObjectIsNotValidException_Exception, AuthenticationException_Exception {
        serviceLocator.getSessionSOAPEndpoint().logout(userData.getSession());
        userData.setSession(null);
        ConsoleHelper.print("Good bye!");
    }
}
