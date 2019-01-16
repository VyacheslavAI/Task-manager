package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.IUserRepository;
import ru.ivanov.todoproject.api.IUserService;
import ru.ivanov.todoproject.dao.UserRepository;
import ru.ivanov.todoproject.entity.User;

import java.util.Collections;
import java.util.List;

public class UserService implements IUserService {

    private IUserRepository userRepository = new UserRepository();

    @Override
    public User createOrUpdateUser(final User user) {
        if (user == null)
            return null;

        return userRepository.merge(user);
    }

    @Override
    public void addAllUser(List<User> users) {
        if (users == null || users.isEmpty()) return;

        userRepository.addAllEntity(users);
    }

    @Override
    public List<User> loadUserByName(String name) {
        if (name == null || name.isEmpty())
            return Collections.emptyList();

        return userRepository.getEntitiesByName(name);
    }

    @Override
    public List<User> loadAllUser() {
        return userRepository.getAllEntity();
    }

    @Override
    public User deleteUser(User user) {
        if (user == null)
            return null;

        return userRepository.deleteEntity(user);
    }

    @Override
    public void deleteAllUser() {
        userRepository.deleteAllEntity();
    }
}
