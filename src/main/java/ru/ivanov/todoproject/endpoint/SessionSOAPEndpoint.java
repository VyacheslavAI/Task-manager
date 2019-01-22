package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SessionSOAPEndpoint {

    private ServiceLocator serviceLocator;

    private Map<User, List<Session>> authorizedUsers = serviceLocator.getUserService().getAuthorizedUsers();

    public boolean userRegistry(final String login, final String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        serviceLocator.getUserService().createOrUpdateUser(user);
        return true;
    }

    public Session singIn(final String login, final String password) {
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

    public boolean signOut(final Session session) {
        final User user = serviceLocator.getUserService().getUserBySession(session);
        return authorizedUsers.get(user).remove(session);
    }

    public boolean fullSignOut(final Session session) {
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
