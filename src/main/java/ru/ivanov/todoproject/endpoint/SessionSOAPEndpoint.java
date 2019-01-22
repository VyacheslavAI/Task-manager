package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionSOAPEndpoint {

    private ServiceLocator serviceLocator;

    private Map<User, List<Session>> authorizedUsers = new HashMap<>();

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

    public User getUserBySession(final Session session) {
        if (session == null) return null;
        for (final Map.Entry<User, List<Session>> authorizedUser : authorizedUsers.entrySet()) {
            final User user = authorizedUser.getKey();
            if (user.getId().equals(session.getUserId())) {
                final List<Session> sessions = authorizedUser.getValue();

            }
        }
        return null;
    }

    public boolean isUserAuthorized(final Session session) {
        for (Map.Entry<User, List<Session>> users : authorizedUsers.entrySet()) {
            final Session userSession = users.getValue();
            if (userSession.getSignature().equals(session.getSignature()))
                return true;
        }
        return false;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
}
