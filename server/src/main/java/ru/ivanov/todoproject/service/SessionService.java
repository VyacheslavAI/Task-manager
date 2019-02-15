package ru.ivanov.todoproject.service;

import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.todoproject.api.ISessionService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.repository.ISessionRepository;
import ru.ivanov.todoproject.util.Validator;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Transactional
public class SessionService implements ISessionService {

    @Inject
    private ISessionRepository sessionRepository;

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private Validator validator;

    @Override
    public Session createSession(final Session session) throws ObjectIsNotValidException {
        if (!validator.isSessionValid(session)) throw new ObjectIsNotValidException();
        return sessionRepository.save(session);
    }

    @Override
    public Session findSessionById(final String sessionId) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(sessionId)) throw new InvalidArgumentException();
        final Session session = sessionRepository.getById(sessionId);
        if (session == null) throw new ObjectNotFoundException();
        return session;
    }

    @Override
    public Session deleteSession(final Session session) throws ObjectIsNotValidException, ObjectNotFoundException {
        if (!validator.isSessionValid(session)) throw new ObjectIsNotValidException();
        if (!sessionRepository.existsById(session.getId())) throw new ObjectNotFoundException();
        sessionRepository.delete(session);
        return session;
    }

    @Override
    public boolean deleteAllSession() {
        sessionRepository.deleteAllInBatch();
        return false;
    }

    @Override
    public boolean addAllSession(final List<Session> sessions) {
        if (sessions == null || sessions.isEmpty()) return false;
        sessions.removeAll(Collections.singletonList(null));
        sessionRepository.saveAll(sessions);
        return true;
    }

    @Override
    public List<Session> findAllSession() {
        return sessionRepository.findAll();
    }
}