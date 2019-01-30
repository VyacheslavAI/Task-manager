package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.IUserRepository;
import ru.ivanov.todoproject.api.IUserService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.security.SecurityServerManager;
import ru.ivanov.todoproject.validator.Validator;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;


public class UserService implements IUserService {

    private IUserRepository userRepository;

    private ServiceLocator serviceLocator;

    private SecurityServerManager securityManager;

    private Validator validator;

    @Override
    public User createOrUpdateUser(final User user) throws ObjectIsNotValidException {
        if (!validator.isUserValid(user)) throw new ObjectIsNotValidException();
        return userRepository.merge(user);
    }

    @Override
    public User loadById(final String userId) throws InvalidArgumentException {
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
        return userRepository.findById(userId);
    }

    @Override
    public User loadUserByLogin(final String login) throws InvalidArgumentException {
        if (!Validator.isArgumentsValid(login)) throw new InvalidArgumentException();
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
    public User getUserBySession(final Session session) throws ObjectIsNotValidException, ObjectNotFoundException {
        if (!validator.isSessionValid(session)) throw new ObjectIsNotValidException();
        final User user = userRepository.findBySession(session.getUserId());
        if (user == null) throw new ObjectNotFoundException();
        return user;
    }

    @Override
    public void userInitialize(final String login, final String password) throws ObjectIsNotValidException, InvalidArgumentException, NoSuchAlgorithmException {
        if (!Validator.isArgumentsValid(login, password)) throw new InvalidArgumentException();
        final User user = new User();
        final String hashPassword = securityManager.getPasswordHash(password);
        user.setLogin(login);
        user.setPasswordHash(hashPassword);
        final Session session = new Session();
        final long currentTimeMillis = System.currentTimeMillis();
        session.setTimestamp(currentTimeMillis);
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
    public void setSecurityServerManager(SecurityServerManager securityManager) {
        this.securityManager = securityManager;
    }

    @Override
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
