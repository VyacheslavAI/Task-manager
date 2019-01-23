package ru.ivanov.todoproject.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IUserService {
    User createOrUpdateUser(User user);

    User loadById(String id);

    User loadUserByLogin(String login);

    List<User> loadAllUser();

    void addAllUser(List<User> users);

    User deleteUser(User user);

    void deleteAllUser();

    User getUserBySession(Session session);

    void userInitialize(String login, String password) throws NoSuchAlgorithmException, JsonProcessingException;

    ServiceLocator getServiceLocator();

    void setServiceLocator(ServiceLocator serviceLocator);
}
