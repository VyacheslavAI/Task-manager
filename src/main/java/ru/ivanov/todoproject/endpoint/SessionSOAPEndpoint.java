package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.ISessionSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.ISessionSOAPEndpoint")
public class SessionSOAPEndpoint implements ISessionSOAPEndpoint {

    private ServiceLocator serviceLocator;

    @Override
    public boolean userRegistry(final String login, final String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        serviceLocator.getUserService().createOrUpdateUser(user);
        return true;
    }

    @Override
    public Session login(final String login, final String password) {
        final Map<User, List<Session>> authorizedUsers = serviceLocator.getUserService().getAuthorizedUsers();
        final User user = serviceLocator.getUserService().loadUserByLogin(login);
        if (!user.getPassword().equals(password)) {
            return null;
        }
        final Session session = Session.createSession();
        if (!authorizedUsers.containsKey(user)) {
            authorizedUsers.put(user, new ArrayList<Session>());
        }
        authorizedUsers.get(user).add(session);
        return session;
    }

    @Override
    public boolean logout(final Session session) {
        final Map<User, List<Session>> authorizedUsers = serviceLocator.getUserService().getAuthorizedUsers();
        final User user = serviceLocator.getUserService().getUserBySession(session);
        return authorizedUsers.get(user).remove(session);
    }

    @Override
    public boolean fullSignOut(final Session session) {
        final Map<User, List<Session>> authorizedUsers = serviceLocator.getUserService().getAuthorizedUsers();
        final User user = serviceLocator.getUserService().getUserBySession(session);
        authorizedUsers.remove(user);
        return true;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
}