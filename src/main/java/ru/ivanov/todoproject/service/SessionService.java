package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.repository.SessionRepository;

import java.util.Collections;
import java.util.List;

import static ru.ivanov.todoproject.util.ValidationUtil.isSessionValid;

public class SessionService implements ru.ivanov.todoproject.api.ISessionService {

    private SessionRepository sessionRepository = new SessionRepository();

    private ServiceLocator serviceLocator;

    @Override
    public Session createOrUpdateSession(final Session session) throws ObjectIsNotValidException {
        if (!isSessionValid(session)) throw new ObjectIsNotValidException(session);
        return sessionRepository.merge(session);
    }

    @Override
    public Session loadSessionById(final String id) throws IllegalArgumentException {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException();
        return sessionRepository.findById(id);
    }

    @Override
    public Session deleteSession(final Session session) throws ObjectIsNotValidException {
        if (!isSessionValid(session)) throw new ObjectIsNotValidException(session);
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
    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    @Override
    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
}