package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;

import java.util.List;

public interface ISessionService {
    Session createOrUpdateSession(Session session) throws ObjectIsNotValidException;

    Session loadSessionById(String id);

    Session deleteSession(Session session) throws ObjectIsNotValidException;

    boolean deleteAllSession();

    boolean addAllSession(List<Session> sessions);

    List<Session> loadAllSession();

    ServiceLocator getServiceLocator();

    void setServiceLocator(ServiceLocator serviceLocator);
}
