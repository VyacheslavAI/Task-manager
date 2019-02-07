package ru.ivanov.todoproject.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import ru.ivanov.todoproject.api.IUserService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.repository.IUserRepository;
import ru.ivanov.todoproject.security.SecurityServerManager;
import ru.ivanov.todoproject.validator.Validator;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

@Transactional
public class UserService implements IUserService {

    @Inject
    private IUserRepository userRepository;

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private SecurityServerManager securityManager;

    @Inject
    private Validator validator;

    @Override
    public User createUser(final User user) throws ObjectIsNotValidException {
        if (!validator.isUserValid(user)) throw new ObjectIsNotValidException();
        return userRepository.save(user);
    }

    @Override
    public User updateUser(final User user) throws ObjectIsNotValidException {
        if (!validator.isUserValid(user)) throw new ObjectIsNotValidException();
        return userRepository.save(user);
    }

    @Override
    public User loadById(final String userId) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
        final User user = userRepository.findBy(userId);
        if (user == null) throw new ObjectNotFoundException();
        return user;
    }

    @Override
    public User loadUserByLogin(final String login) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(login)) throw new InvalidArgumentException();
        final User user = userRepository.findByLogin(login);
        if (user == null) throw new ObjectNotFoundException();
        return user;
    }

    @Override
    public List<User> loadAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean addAllUser(final List<User> users) {
        if (users == null || users.isEmpty()) return false;
        users.removeAll(Collections.singleton(null));
        for (final User user : users) {
            userRepository.save(user);
        }
        return true;
    }

    @Override
    public User deleteUser(final User user) throws ObjectIsNotValidException {
        if (!validator.isUserValid(user)) throw new ObjectIsNotValidException();
        userRepository.attachAndRemove(user);
        return user;
    }

    @Override
    public boolean deleteAllUser() {
        userRepository.deleteAllUser();
        return true;
    }

    @Override
    public void userInitialize(final String login, final String password) throws ObjectIsNotValidException, InvalidArgumentException, NoSuchAlgorithmException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(login, password)) throw new InvalidArgumentException();
        if (isUserExists(login)) return;
        final User user = new User();
        final String hashPassword = securityManager.getPasswordHash(password);
        user.setLogin(login);
        user.setPasswordHash(hashPassword);
        final Session session = new Session();
        final long currentTimeMillis = System.currentTimeMillis();
        session.setTimestamp(currentTimeMillis);
        session.setUserId(user.getId());
        session.setSignature(securityManager.sign(session));
        createUser(user);
        serviceLocator.getSessionService().createSession(session);
    }

    private boolean isUserExists(final String login) {
        final User user = userRepository.findByLogin(login);
        return user != null;
    }
}
