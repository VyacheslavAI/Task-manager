package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Session;

public interface ISessionRepository extends IRepository<Session> {
    Session createSession(Session session);

    Session updateSession(Session session);
}
