package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.IUserSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.security.SecurityServerManager;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.IUserSOAPEndpoint")
public class UserSOAPEndpoint implements IUserSOAPEndpoint {

    private ServiceLocator serviceLocator;

    private SecurityServerManager securityManager;

    @Override
    public User createUser(final Session session, final User user) {
        return null;
    }

    @Override
    public User readUser(final Session session, final String login) {
        return null;
    }

    @Override
    public User updateUser(final Session session, final User user) {
        return null;
    }

    @Override
    public User deleteUser(final Session session, final User user) {
        return null;
    }

    @Override
    public List<User> showUsers(final Session session) {
        return null;
    }

    @Override
    public User getUser(final Session session) {
        return null;
    }

//    @Override
//    public void setServiceLocator(ServiceLocator serviceLocator) {
//        this.serviceLocator = serviceLocator;
//    }

    @Override
    public void setSecurityServerManager(SecurityServerManager securityServerManager) {
        this.securityManager = securityServerManager;
    }
}
