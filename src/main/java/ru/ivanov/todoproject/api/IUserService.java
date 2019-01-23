package ru.ivanov.todoproject.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IUserService {
    User createOrUpdateUser(User user) throws ObjectIsNotValidException;

    User loadById(String id);

    User loadUserByLogin(String login);

    List<User> loadAllUser();

    boolean addAllUser(List<User> users);

    User deleteUser(User user) throws ObjectIsNotValidException;

    boolean deleteAllUser();

    User getUserBySession(Session session) throws ObjectIsNotValidException;

    void userInitialize(String login, String password) throws NoSuchAlgorithmException, JsonProcessingException, ObjectIsNotValidException;

    ServiceLocator getServiceLocator();

    void setServiceLocator(ServiceLocator serviceLocator);
}
