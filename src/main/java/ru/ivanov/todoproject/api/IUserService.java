package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.User;

import java.util.List;

public interface IUserService {

    User createOrUpdateUser(final User user);

    void addAllUser(List<User> users);

    List<User> loadUserByName(final String name);

    List<User> loadAllUser();

    User deleteUser(User user);

    void deleteAllUser();
}
