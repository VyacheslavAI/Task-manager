package ru.ivanov.todoproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.api.IUserRepository;
import ru.ivanov.todoproject.api.IUserService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.repository.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

import static ru.ivanov.todoproject.util.HashUtil.getHashByAlgorithm;
import static ru.ivanov.todoproject.util.HashUtil.sign;
import static ru.ivanov.todoproject.util.ValidationUtil.isSessionValid;
import static ru.ivanov.todoproject.util.ValidationUtil.isUserValid;

public class UserService implements IUserService {

    private final IUserRepository userRepository = new UserRepository();

    private ServiceLocator serviceLocator;

    @Override
    public User createOrUpdateUser(final User user) throws ObjectIsNotValidException {
        if (!isUserValid(user)) throw new ObjectIsNotValidException(user);
        return userRepository.merge(user);
    }

    @Override
    public User loadById(final String id) throws IllegalArgumentException {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException();
        return userRepository.findById(id);
    }

    @Override
    public User loadUserByLogin(final String login) throws IllegalArgumentException {
        if (login == null || login.isEmpty()) throw new IllegalArgumentException();
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> loadAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean addAllUser(final List<User> users) {
        if (users == null || users.isEmpty()) return false;
        users.removeAll(Collections.singleton(null));
        return userRepository.addAll(users);
    }

    @Override
    public User deleteUser(final User user) throws ObjectIsNotValidException {
        if (!isUserValid(user)) throw new ObjectIsNotValidException(user);
        return userRepository.delete(user);
    }

    @Override
    public boolean deleteAllUser() {
        return userRepository.deleteAll();
    }

    @Override
    public User getUserBySession(final Session session) throws ObjectIsNotValidException {
        if (!isSessionValid(session)) throw new ObjectIsNotValidException(session);
        final List<User> users = loadAllUser();
        for (User user : users) {
            if (user.getId().equals(session.getUserId())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void userInitialize(final String login, final String password) throws NoSuchAlgorithmException,
            ObjectIsNotValidException, IllegalArgumentException {
        if (login == null || login.isEmpty()) throw new IllegalArgumentException();
        if (password == null || password.isEmpty()) throw new IllegalArgumentException();
        final String hashPassword = getHashByAlgorithm("MD5", password);
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(hashPassword);
        final Session session = new Session();
        session.setTimestamp(session.getCreated().getTime());
        session.setUserId(user.getId());
        session.setSignature(sign(session));
        createOrUpdateUser(user);
        serviceLocator.getSessionService().createOrUpdateSession(session);
    }

    @Override
    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    @Override
    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
}
