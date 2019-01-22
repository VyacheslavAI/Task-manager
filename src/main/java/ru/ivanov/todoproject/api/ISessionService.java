package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Session;

import java.util.List;

public interface ISessionService {
    Session createOrUpdateSession(final Session session);

    Session deleteSession(final Session session);

    void deleteAllSession();

    void addAllSession(final List<Session> sessions);

    List<Session> loadAllSession();
}
