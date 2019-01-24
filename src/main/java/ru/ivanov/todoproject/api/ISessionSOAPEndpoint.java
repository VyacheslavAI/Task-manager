package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.RequestNotAuthenticatedException;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ISessionSOAPEndpoint {

    @WebMethod
    boolean userRegistry(String login, String password) throws ObjectIsNotValidException;

    @WebMethod
    Session login(String login, String password) throws ObjectIsNotValidException;

    @WebMethod
    boolean logout(Session session) throws RequestNotAuthenticatedException, ObjectIsNotValidException;

    @WebMethod
    boolean fullSignOut(Session session) throws RequestNotAuthenticatedException;

    ServiceLocator getServiceLocator();

    void setServiceLocator(ServiceLocator serviceLocator);
}
