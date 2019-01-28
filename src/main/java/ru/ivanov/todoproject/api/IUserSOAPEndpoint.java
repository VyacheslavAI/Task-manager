package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.security.SecurityServerManager;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IUserSOAPEndpoint {

    @WebMethod
    User createUser(final Session session, final User user);

    @WebMethod
    User readUser(final Session session, final String login);

    @WebMethod
    User updateUser(final Session session, final User user);

    @WebMethod
    User deleteUser(final Session session, final User user);

    @WebMethod
    List<User> showUsers(final Session session);

    @WebMethod
    User getUser(final Session session);

    void setServiceLocator(ServiceLocator serviceLocator);

    void setSecurityManager(SecurityServerManager securityManager);
}
