package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Session;

import java.util.List;

public interface ISessionService {
    Session createOrUpdateSession(Session session);

    Session loadSessionById(String id);

    Session deleteSession(Session session);

    void deleteAllSession();

    void addAllSession(List<Session> sessions);

    List<Session> loadAllSession();

    ServiceLocator getServiceLocator();

    void setServiceLocator(ServiceLocator serviceLocator);
}
