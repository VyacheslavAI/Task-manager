package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.validator.Validator;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@XmlJavaTypeAdapter(ServiceLocator.SessionServiceAdapter.class)
public interface ISessionService {

    Session createSession(Session session) throws ObjectIsNotValidException;

    Session loadSessionById(String id) throws InvalidArgumentException;

    Session deleteSession(Session session) throws ObjectIsNotValidException;

    boolean deleteAllSession();

    boolean addAllSession(List<Session> sessions);

    List<Session> loadAllSession();

    void setServiceLocator(ServiceLocator serviceLocator);

    void setValidator(Validator validator);

    void setSessionRepository(ISessionRepository sessionRepository);
}
