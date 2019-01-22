package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SessionSOAPEndpoint {

    private ServiceLocator serviceLocator;

    private Map<User, List<Session>> authorizedUsers = new HashMap<>();

    private static final SessionSOAPEndpoint SESSION_SOAP_ENDPOINT = new SessionSOAPEndpoint();

    public static SessionSOAPEndpoint getInstance() {
        return SESSION_SOAP_ENDPOINT;
    }

    private SessionSOAPEndpoint() {
    }

    public boolean userRegistry(final String login, final String password) {
        return false;
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
        final User user = getUserBySession(session);
        return authorizedUsers.get(user).remove(session);
    }

    public boolean fullSignOut(final Session session) {
        final User user = getUserBySession(session);
        authorizedUsers.remove(user);
        return true;
    }

    User getUserBySession(final Session session) {
        if (session == null) return null;
        for (final Map.Entry<User, List<Session>> authorizedUser : authorizedUsers.entrySet()) {
            List<Session> userSessions = authorizedUser.getValue();
            if (userSessions.contains(session)) {
                return authorizedUser.getKey();
            }
        }
        return null;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
}
