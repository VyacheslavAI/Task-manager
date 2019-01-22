package ru.ivanov.todoproject.dao;

import ru.ivanov.todoproject.api.IRepository;
import ru.ivanov.todoproject.entity.Session;

public class SessionRepository extends AbstractRepository<Session> implements IRepository<Session> {

    public Session findSessionBySignature(final String signature) {
        for (Session session : entities.values()) {
            if (session.getSignature().equals(signature)) {
                return session;
            }
        }
        return null;
    }
}
