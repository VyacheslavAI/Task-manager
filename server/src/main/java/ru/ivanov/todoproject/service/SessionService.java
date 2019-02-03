package ru.ivanov.todoproject.service;

import org.hibernate.SessionFactory;
import ru.ivanov.todoproject.api.ISessionRepository;
import ru.ivanov.todoproject.api.ISessionService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.validator.Validator;

import java.util.Collections;
import java.util.List;

public class SessionService implements ISessionService {

    private ISessionRepository sessionRepository;

    private ServiceLocator serviceLocator;

    private Validator validator;

    private SessionFactory sessionFactory;

    @Override
    public Session createSession(final Session session) throws ObjectIsNotValidException {
        if (!validator.isSessionValid(session)) throw new ObjectIsNotValidException();
//        return sessionRepository.createSession(session);

        try (final org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
            hibernateSession.beginTransaction();
            hibernateSession.save(session);
            hibernateSession.getTransaction().commit();
        }
        return session;
    }

    @Override
    public Session loadSessionById(final String sessionId) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(sessionId)) throw new InvalidArgumentException();
//        return sessionRepository.findById(sessionId);

        try (final org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
            hibernateSession.beginTransaction();
            final Session session = hibernateSession.get(Session.class, sessionId);
            hibernateSession.getTransaction().commit();
            if (session == null) throw new ObjectNotFoundException();
            return session;
        }
    }

    @Override
    public Session deleteSession(final Session session) throws ObjectIsNotValidException {
        if (!validator.isSessionValid(session)) throw new ObjectIsNotValidException();
//        return sessionRepository.deleteSession(session);

        try (final org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
            hibernateSession.beginTransaction();
            hibernateSession.delete(session);
            hibernateSession.getTransaction().commit();
        }
        return session;
    }

    @Override
    public boolean deleteAllSession() {
//        return sessionRepository.deleteAllSession();
        return false;
    }

    @Override
    public boolean addAllSession(final List<Session> sessions) {
        if (sessions == null || sessions.isEmpty()) return false;
//        sessions.removeAll(Collections.singletonList(null));
//        return sessionRepository.addAll(sessions);
        return false;
    }

    @Override
    public List<Session> loadAllSession() {
//        return sessionRepository.findAll();
        return Collections.emptyList();
    }

    @Override
    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void setSessionRepository(ISessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}