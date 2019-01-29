package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.userdata.UserData;

public abstract class AbstractCommand {

    ServiceLocator serviceLocator;

    UserData userData;

    public abstract String getConsoleCommand();

    public abstract String getDescription();

    public abstract boolean isAuthorizationRequired();

    public abstract void execute() throws ObjectIsNotValidException_Exception, NoSuchAlgorithmException_Exception, JsonProcessingException_Exception, AuthenticationException_Exception, InvalidArgumentException_Exception, ObjectNotFoundException_Exception, AuthorizationException_Exception;

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
