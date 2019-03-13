package ru.ivanov.todoproject.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IUserService {

    User createUser(User user) throws ObjectIsNotValidException;

    User updateUser(User user) throws ObjectIsNotValidException;

    User findById(String id) throws InvalidArgumentException, ObjectNotFoundException;

    User loadUserByLogin(String login) throws InvalidArgumentException, ObjectNotFoundException;

    List<User> loadAllUser();

    boolean addAllUser(List<User> users);

    User deleteUser(User user) throws ObjectIsNotValidException, ObjectNotFoundException;

    boolean deleteAllUser();

    void userInitialize(String login, String password) throws ObjectIsNotValidException, InvalidArgumentException, NoSuchAlgorithmException, ObjectNotFoundException;
}
