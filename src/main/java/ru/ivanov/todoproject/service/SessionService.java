package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.repository.SessionRepository;

import java.util.List;

public class SessionService implements ru.ivanov.todoproject.api.ISessionService {

    private SessionRepository sessionRepository = new SessionRepository();

    private ServiceLocator serviceLocator;

    @Override
    public Session createOrUpdateSession(final Session session) {
        if (session == null) return null;
        return sessionRepository.merge(session);
    }

    @Override
    public Session loadSessionById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return sessionRepository.findById(id);
    }

    @Override
    public Session deleteSession(final Session session) {
        if (session == null) return null;
        return sessionRepository.delete(session);
    }

    @Override
    public void deleteAllSession() {
        sessionRepository.deleteAll();
    }

    @Override
    public void addAllSession(final List<Session> sessions) {
        sessionRepository.addAll(sessions);
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