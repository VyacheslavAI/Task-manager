package ru.ivanov.todoproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.api.IUserRepository;
import ru.ivanov.todoproject.api.IUserService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.repository.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static ru.ivanov.todoproject.util.HashUtil.getHashByAlgorithm;
import static ru.ivanov.todoproject.util.HashUtil.sign;

public class UserService implements IUserService {

    private final IUserRepository userRepository = new UserRepository();

    private ServiceLocator serviceLocator;

    @Override
    public User createOrUpdateUser(final User user) {
        if (user == null) return null;
        return userRepository.merge(user);
    }

    @Override
    public User loadById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return userRepository.findById(id);
    }

    @Override
    public User loadUserByLogin(final String login) {
        if (login == null || login.isEmpty()) return null;
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> loadAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void addAllUser(final List<User> users) {
        if (users == null || users.isEmpty()) return;
        userRepository.addAll(users);
    }

    @Override
    public User deleteUser(final User user) {
        if (user == null) return null;
        return userRepository.delete(user);
    }

    @Override
    public void deleteAllUser() {
        userRepository.deleteAll();
    }

    @Override
    public User getUserBySession(final Session session) {
        if (session == null) return null;
        final List<User> users = loadAllUser();
        for (User user : users) {
            if (user.getId().equals(session.getUserId())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void userInitialize(final String login, final String password) throws NoSuchAlgorithmException, JsonProcessingException {
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
