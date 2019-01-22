package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.ISessionService;
import ru.ivanov.todoproject.dao.SessionRepository;
import ru.ivanov.todoproject.entity.Session;

import java.util.List;

public class SessionService implements ISessionService {

    private SessionRepository sessionRepository = new SessionRepository();

    @Override
    public Session createOrUpdateSession(final Session session) {
        if (session == null) return null;
        return sessionRepository.merge(session);
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
}
