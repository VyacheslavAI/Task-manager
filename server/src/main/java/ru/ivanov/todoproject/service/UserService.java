package ru.ivanov.todoproject.service;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.ivanov.todoproject.api.IUserRepository;
import ru.ivanov.todoproject.api.IUserService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
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

    private SessionFactory sessionFactory;

    @Override
    public User createUser(final User user) throws ObjectIsNotValidException {
        if (!validator.isUserValid(user)) throw new ObjectIsNotValidException();
//        return userRepository.createUser(user);

        try (final org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
            hibernateSession.beginTransaction();
            hibernateSession.save(user);
            hibernateSession.getTransaction().commit();
        }
        return user;
    }

    @Override
    public User updateUser(final User user) throws ObjectIsNotValidException {
        if (!validator.isUserValid(user)) throw new ObjectIsNotValidException();
//        return userRepository.updateUser(user);

        try (final org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
            hibernateSession.beginTransaction();
            hibernateSession.update(user);
            hibernateSession.getTransaction().commit();
        }
        return user;
    }

    @Override
    public User loadById(final String userId) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
//        return userRepository.findById(userId);

        try (final org.hibernate.Session session = sessionFactory.openSession()) {
            final User user = session.get(User.class, userId);
            if (user == null) throw new ObjectNotFoundException();
            return user;
        }
    }

    @Override
    public User loadUserByLogin(final String login) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(login)) throw new InvalidArgumentException();
//        return userRepository.findByLogin(login);

        try (final org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
            hibernateSession.beginTransaction();
            final Query query = hibernateSession.createQuery("from User where login = :login");
            query.setParameter("login", login);
            final User user = (User) query.uniqueResult();
            hibernateSession.getTransaction().commit();
//            if (user == null) throw new ObjectNotFoundException();
            return user;
        }
    }

    @Override
    public List<User> loadAllUser() {
//        return userRepository.findAll();

        try (final org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
            hibernateSession.beginTransaction();
            final Query query = hibernateSession.createQuery("from User");
            final List<User> users = query.getResultList();
            hibernateSession.getTransaction().commit();
            return users;
        }
    }

    @Override
    public boolean addAllUser(final List<User> users) {
        if (users == null || users.isEmpty()) return false;
        users.removeAll(Collections.singleton(null));
//        return userRepository.addAll(users);

        try (final org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
            hibernateSession.beginTransaction();
            for (final User user : users) {
                hibernateSession.save(user);
            }
            hibernateSession.getTransaction().commit();
        }
        return true;
    }

    @Override
    public User deleteUser(final User user) throws ObjectIsNotValidException, ObjectNotFoundException {
        if (!validator.isUserValid(user)) throw new ObjectIsNotValidException();
//        return userRepository.deleteUser(user);

        try (final org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
            hibernateSession.beginTransaction();
            final Query query = hibernateSession.createQuery("from User where id = :userId");
            query.setParameter("userId", user.getId());
            final User persistentUser = (User) query.uniqueResult();
            if (persistentUser == null) throw new ObjectNotFoundException();
            hibernateSession.delete(persistentUser);
            hibernateSession.getTransaction().commit();
            return user;
        }
    }

    @Override
    public boolean deleteAllUser() {
//        return userRepository.deleteAllUser();
        return false;
    }

    @Override
    public User getUserBySession(final Session session) throws ObjectIsNotValidException, ObjectNotFoundException {
        if (!validator.isSessionValid(session)) throw new ObjectIsNotValidException();
//        final User user = userRepository.findBySession(session.getUserId());
//        if (user == null) throw new ObjectNotFoundException();
//        return user;
        return null;
    }

    @Override
    public void userInitialize(final String login, final String password) throws ObjectIsNotValidException, InvalidArgumentException, NoSuchAlgorithmException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(login, password)) throw new InvalidArgumentException();
        final User targetUser = loadUserByLogin(login);
        if (targetUser != null) return;
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

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
