package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.IUserRepository;
import ru.ivanov.todoproject.api.IUserService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.repository.UserRepository;
import ru.ivanov.todoproject.security.SecurityServerManager;
import ru.ivanov.todoproject.validator.Validator;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;


public class UserService implements IUserService {

    private final IUserRepository userRepository = new UserRepository();

    private ServiceLocator serviceLocator;

    private SecurityServerManager securityManager;

    private Validator validator;

    @Override
    public User createOrUpdateUser(final User user) throws ObjectIsNotValidException {
        if (!validator.isUserValid(user)) throw new ObjectIsNotValidException();
        return userRepository.merge(user);
    }

    @Override
    public User loadById(final String id) throws InvalidArgumentException {
        if (id == null || id.isEmpty()) throw new InvalidArgumentException();
        return userRepository.findById(id);
    }

    @Override
    public User loadUserByLogin(final String login) throws InvalidArgumentException {
        if (login == null || login.isEmpty()) throw new InvalidArgumentException();
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
        if (!validator.isUserValid(user)) throw new ObjectIsNotValidException();
        return userRepository.delete(user);
    }

    @Override
    public boolean deleteAllUser() {
        return userRepository.deleteAll();
    }

    @Override
    public User getUserBySession(final Session session) throws ObjectIsNotValidException {
        if (!validator.isSessionValid(session)) throw new ObjectIsNotValidException();
        final List<User> users = loadAllUser();
        for (final User user : users) {
            if (user.getId().equals(session.getUserId())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void userInitialize(final String login, final String password) throws ObjectIsNotValidException, InvalidArgumentException, NoSuchAlgorithmException {
        if (login == null || login.isEmpty()) throw new InvalidArgumentException();
        if (password == null || password.isEmpty()) throw new InvalidArgumentException();
        final User user = new User();
        final String hashPassword = securityManager.getPasswordHash(password);
        user.setLogin(login);
        user.setPasswordHash(hashPassword);
        final Session session = new Session();
        session.setTimestamp(session.getCreated().getTime());
        session.setUserId(user.getId());
        session.setSignature(securityManager.sign(session));
        createOrUpdateUser(user);
        serviceLocator.getSessionService().createOrUpdateSession(session);
    }

    @Override
    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void setSecurityManager(SecurityServerManager securityManager) {
        this.securityManager = securityManager;
    }

    @Override
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
