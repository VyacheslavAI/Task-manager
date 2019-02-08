package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.ISessionSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.*;
import ru.ivanov.todoproject.security.SecurityServerManager;
import ru.ivanov.todoproject.validator.Validator;

import javax.inject.Inject;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.ISessionSOAPEndpoint")
public class SessionSOAPEndpoint implements ISessionSOAPEndpoint {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private SecurityServerManager securityManager;

    @Override
    public boolean userRegistry(final String login, final String passwordHash) throws ObjectIsNotValidException {
        if (!Validator.isArgumentsValid(login, passwordHash)) return false;
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(passwordHash);
        serviceLocator.getUserService().createUser(user);
        return true;
    }

    @Override
    public Session login(final String login, final String passwordHash) throws ObjectIsNotValidException, InvalidArgumentException, AuthorizationException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(login, passwordHash)) throw new InvalidArgumentException();
        final User user = serviceLocator.getUserService().loadUserByLogin(login);
        if (user == null) throw new AuthorizationException();
        if (!user.getPasswordHash().equals(passwordHash)) throw new AuthorizationException();
        final Session userSession = new Session();
        userSession.setUserId(user.getId());
        final long currentTimeMillis = System.currentTimeMillis();
        userSession.setTimestamp(currentTimeMillis);
        final String signature = securityManager.sign(userSession);
        userSession.setSignature(signature);
        serviceLocator.getSessionService().createSession(userSession);
        return userSession;
    }

    @Override
    public boolean logout(final Session session) throws AuthenticationException, ObjectIsNotValidException, ObjectNotFoundException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        serviceLocator.getSessionService().deleteSession(session);
        return true;
    }

    @Override
    public boolean fullSignOut(final Session session) throws AuthenticationException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        serviceLocator.getSessionService().deleteAllSession();
        return true;
    }
}