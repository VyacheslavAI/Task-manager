package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.ServiceLocator;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.api.AuthenticationException_Exception;
import ru.ivanov.todoproject.api.AuthorizationException_Exception;
import ru.ivanov.todoproject.api.InvalidArgumentException_Exception;
import ru.ivanov.todoproject.api.JsonProcessingException_Exception;
import ru.ivanov.todoproject.api.NoSuchAlgorithmException_Exception;
import ru.ivanov.todoproject.api.ObjectIsNotValidException_Exception;
import ru.ivanov.todoproject.api.ObjectNotFoundException_Exception;
import ru.ivanov.todoproject.security.SecurityClientManager;
import ru.ivanov.todoproject.userdata.UserData;

import java.security.NoSuchAlgorithmException;

public abstract class AbstractCommand {

    ServiceLocator serviceLocator;

    UserData userData;

    SecurityClientManager securityClientManager;

    public abstract String getConsoleCommand();

    public abstract String getDescription();

    public abstract boolean isAuthorizationRequired();

    public abstract void execute() throws ObjectIsNotValidException_Exception, NoSuchAlgorithmException_Exception, JsonProcessingException_Exception, AuthenticationException_Exception, InvalidArgumentException_Exception, ObjectNotFoundException_Exception, AuthorizationException_Exception, NoSuchAlgorithmException;

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void setSecurityClientManager(SecurityClientManager securityClientManager) {
        this.securityClientManager = securityClientManager;
    }
}
