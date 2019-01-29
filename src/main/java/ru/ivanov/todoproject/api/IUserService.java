package ru.ivanov.todoproject.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.security.SecurityServerManager;
import ru.ivanov.todoproject.validator.Validator;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IUserService {
    User createOrUpdateUser(User user) throws ObjectIsNotValidException;

    User loadById(String id) throws InvalidArgumentException;

    User loadUserByLogin(String login) throws InvalidArgumentException;

    List<User> loadAllUser();

    boolean addAllUser(List<User> users);

    User deleteUser(User user) throws ObjectIsNotValidException;

    boolean deleteAllUser();

    User getUserBySession(Session session) throws ObjectIsNotValidException, ObjectNotFoundException;

    void userInitialize(String login, String password) throws NoSuchAlgorithmException, JsonProcessingException, ObjectIsNotValidException, InvalidArgumentException;

    void setServiceLocator(ServiceLocator serviceLocator);

    void setSecurityManager(SecurityServerManager securityManager);

    void setValidator(Validator validator);
}
