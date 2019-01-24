package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.ISessionSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.RequestNotAuthenticatedException;

import javax.jws.WebService;

import static ru.ivanov.todoproject.util.HashUtil.isSessionVerified;
import static ru.ivanov.todoproject.util.HashUtil.sign;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.ISessionSOAPEndpoint")
public class SessionSOAPEndpoint implements ISessionSOAPEndpoint {

    private ServiceLocator serviceLocator;

    @Override
    public boolean userRegistry(final String login, final String passwordHash) throws ObjectIsNotValidException {
        if (login == null || login.isEmpty()) return false;
        if (passwordHash == null || passwordHash.isEmpty()) return false;
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(passwordHash);
        serviceLocator.getUserService().createOrUpdateUser(user);
        return true;
    }

    @Override
    public Session login(final String login, final String passwordHash) throws IllegalArgumentException, ObjectIsNotValidException {
        if (login == null || login.isEmpty()) throw new IllegalArgumentException();
        if (passwordHash == null || passwordHash.isEmpty()) throw new IllegalArgumentException();
        final User user = serviceLocator.getUserService().loadUserByLogin(login);
        final Session userSession = new Session();
        userSession.setUserId(user.getId());
        userSession.setTimestamp(System.currentTimeMillis());
        final String signature = sign(userSession);
        userSession.setSignature(signature);
        serviceLocator.getSessionService().createOrUpdateSession(userSession);
        return userSession;
    }

    @Override
    public boolean logout(final Session session) throws RequestNotAuthenticatedException, ObjectIsNotValidException {
        if (isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        serviceLocator.getSessionService().deleteSession(session);
        return true;
    }

    @Override
    public boolean fullSignOut(final Session session) throws RequestNotAuthenticatedException {
        if (isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        serviceLocator.getSessionService().deleteAllSession();
        return true;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
}