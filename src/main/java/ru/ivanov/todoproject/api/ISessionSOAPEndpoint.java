package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ISessionSOAPEndpoint {

    @WebMethod
    boolean userRegistry(String login, String password);

    @WebMethod
    Session singIn(String login, String password);

    @WebMethod
    boolean signOut(Session session);

    @WebMethod
    boolean fullSignOut(Session session);
}
