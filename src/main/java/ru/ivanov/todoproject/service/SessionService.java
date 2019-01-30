package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.ISessionRepository;
import ru.ivanov.todoproject.api.ISessionService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.repository.SessionRepository;
import ru.ivanov.todoproject.validator.Validator;

import java.util.Collections;
import java.util.List;

public class SessionService implements ISessionService {

    private ISessionRepository sessionRepository;

    private ServiceLocator serviceLocator;

    private Validator validator;

    @Override
    public Session createOrUpdateSession(final Session session) throws ObjectIsNotValidException {
        if (!validator.isSessionValid(session)) throw new ObjectIsNotValidException();
        return sessionRepository.merge(session);
    }

    @Override
    public Session loadSessionById(final String sessionId) throws InvalidArgumentException {
        if (!Validator.isArgumentsValid(sessionId)) throw new InvalidArgumentException();
        return sessionRepository.findById(sessionId);
    }

    @Override
    public Session deleteSession(final Session session) throws ObjectIsNotValidException {
        if (!validator.isSessionValid(session)) throw new ObjectIsNotValidException();
        return sessionRepository.delete(session);
    }

    @Override
    public boolean deleteAllSession() {
        return sessionRepository.deleteAll();
    }

    @Override
    public boolean addAllSession(final List<Session> sessions) {
        if (sessions == null || sessions.isEmpty()) return false;
        sessions.removeAll(Collections.singletonList(null));
        return sessionRepository.addAll(sessions);
    }

    @Override
    public List<Session> loadAllSession() {
        return sessionRepository.findAll();
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
}