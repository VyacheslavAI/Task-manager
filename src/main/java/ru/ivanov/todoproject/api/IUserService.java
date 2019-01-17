package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.User;

import java.util.List;

public interface IUserService {

    User createOrUpdateUser(final User user);

    void addAllUser(final List<User> users);

    User loadById(final String id);

    User loadUserByLogin(final String login);

    List<User> loadAllUser();

    User deleteUser(final User user);

    void deleteAllUser();
}
