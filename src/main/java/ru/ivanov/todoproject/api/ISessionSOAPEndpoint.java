package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.*;
import ru.ivanov.todoproject.security.SecurityServerManager;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ISessionSOAPEndpoint {

    @WebMethod
    boolean userRegistry(String login, String password) throws ObjectIsNotValidException;

    @WebMethod
    Session login(String login, String password) throws ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException, AuthorizationException;

    @WebMethod
    boolean logout(Session session) throws AuthenticationException, ObjectIsNotValidException;

    @WebMethod
    boolean fullSignOut(Session session) throws AuthenticationException;

//    void setServiceLocator(ServiceLocator serviceLocator);

    void setSecurityServerManager(SecurityServerManager securityManager);
}
