package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import java.util.List;

public interface ISessionService {

    Session createSession(Session session) throws ObjectIsNotValidException;

    Session findSessionById(String id) throws InvalidArgumentException, ObjectNotFoundException;

    Session deleteSession(Session session) throws ObjectIsNotValidException, ObjectNotFoundException;

    boolean deleteAllSession();

    boolean addAllSession(List<Session> sessions);

    List<Session> findAllSession();
}
