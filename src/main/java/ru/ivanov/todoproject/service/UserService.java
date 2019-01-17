package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.IUserRepository;
import ru.ivanov.todoproject.api.IUserService;
import ru.ivanov.todoproject.dao.UserRepository;
import ru.ivanov.todoproject.entity.User;

import java.util.List;

public class UserService implements IUserService {

    private final IUserRepository userRepository = new UserRepository();

    @Override
    public User createOrUpdateUser(final User user) {
        if (user == null) return null;
        return userRepository.merge(user);
    }

    @Override
    public User loadById(final String id) {
        return userRepository.findById(id);
    }

    @Override
    public User loadUserByLogin(final String login) {
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
}
